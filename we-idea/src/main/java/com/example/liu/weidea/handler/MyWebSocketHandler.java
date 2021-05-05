package com.example.liu.weidea.handler;

import com.example.liu.weidea.entity.Msg;
import com.example.liu.weidea.service.MsgService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
//package com.xm.ggn.netty;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

/**
 * 自定义服务器端处理handler，继承SimpleChannelInboundHandler，处理WebSocket 连接数据
 */
@Slf4j
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 用户id=>channel示例
    // 可以通过用户的唯一标识保存用户的channel
    // 这样就可以发送给指定的用户
    public static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 每当服务端收到新的客户端连接时,客户端的channel存入ChannelGroup列表中,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 获取连接的channel
        Channel incomming = ctx.channel();
        //通知所有已经连接到服务器的客户端，有一个新的通道加入
        /*for(Channel channel:channelGroup){
            channel.writeAndFlush("[SERVER]-"+incomming.remoteAddress()+"加入\n");
        }*/
        channelGroup.add(incomming);
        log.info("添加新的channel, incomming: {}", incomming);
    }

    /**
     * 每当服务端断开客户端连接时,客户端的channel从ChannelGroup中移除,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //获取连接的channel
        /*Channel incomming = ctx.channel();
        for(Channel channel:channelGroup){
            channel.writeAndFlush("[SERVER]-"+incomming.remoteAddress()+"离开\n");
        }*/
        //从服务端的channelGroup中移除当前离开的客户端
        channelGroup.remove(ctx.channel());

        //从服务端的channelMap中移除当前离开的客户端
        Collection<Channel> col = channelMap.values();
        while (true == col.contains(ctx.channel())) {
            col.remove(ctx.channel());
            log.info("netty客户端连接删除成功!");
        }

    }

    /**
     * 处理建立连接时候请求(用于拿参数)
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (null != msg && msg instanceof FullHttpRequest) {
            log.info("连接请求，准备提取参数");
            //转化为http请求
            FullHttpRequest request = (FullHttpRequest) msg;
            //拿到请求地址
            String uri = request.uri();
            log.info("uri: " + uri);
            if (StringUtils.isNotBlank(uri)) {
//                String path = StringUtils.substringBefore(uri, "?");
//                log.info("path: {}", path);
                String username = StringUtils.substringAfterLast(uri, "=");
                log.info(username);
                channelMap.put(username, ctx.channel());
                log.info("channelMap: {}", channelMap);
            }

            //重新设置请求地址为WebSocketServerProtocolHandler 匹配的地址(如果WebSocketServerProtocolHandler 的时候checkStartsWith   为true则不需要设置，会根据前缀匹配)
//            request.setUri("/ws");
        }

        //接着建立请求
        super.channelRead(ctx, msg);
    }

    /**
     * 每当从服务端读到客户端写入信息时,将信息转发给其他客户端的Channel.
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("netty客户端收到服务器数据, 客户端地址: {}, msg: {}", ctx.channel().remoteAddress(), msg.text());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //消息处理类
        handleMessage(ctx, msg.text(), date);
        //channelGroup.writeAndFlush( new TextWebSocketFrame(msg.text()));
    }

    /**
     * 当服务端的IO 抛出异常时被调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        log.error("SimpleChatClient:" + incoming.remoteAddress() + "异常", cause);
        //异常出现就关闭连接
        ctx.close();
    }

    /**
     * 处理读取到的消息
     *
     * @param ctx
     * @param msg
     * @param date
     */
    private void handleMessage(ChannelHandlerContext ctx, String msg, String date) {
        try {
            // 消息入库
            Msg msgObj = JSONObject.parseObject(msg, Msg.class);
//            log.info("chatLog: {}", msgObj);//sendUserId改为userId；setCid
//            ChatLogService chatLogService = SpringBootUtils.getBean(MsgService.class);
//            chatLogService.insert(msgObj);
//            Date msgDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
//            msgObj.setDate(msgDate);
            System.out.println(msgObj);

            String receiveUsername = String.valueOf(msgObj.getReceiveUserId());
            String sendUsername = String.valueOf(msgObj.getUserId());

            // 消息转发给对应用户(发给发送者和接收者)
//            String receiveUsername = msgObj.getReceiveUsername();
//            String sendUsername = msgObj.getSendUsername();
            Set<Map.Entry<String, Channel>> entries = channelMap.entrySet();
            String key = null;
            for (Map.Entry<String, Channel> entry : entries) {
                key = entry.getKey();
                if (key.equals(receiveUsername) || key.equals(sendUsername)) {
                    log.info("服务器转发消息, key： {}, msg: {}", key, JSONObject.toJSONString(msgObj));
                    entry.getValue().writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(msgObj)));
                }
            }
        } catch (Exception e) {
            log.error("message 处理异常， msg: {}, date: {}", msg, date, e);
        }
    }
}