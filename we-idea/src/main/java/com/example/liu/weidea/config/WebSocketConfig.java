package com.example.liu.weidea.config;

import com.example.liu.weidea.controller.ArticleController;
import com.example.liu.weidea.dao.UserDao;
import com.example.liu.weidea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private List<User> users;

    @Autowired
    UserDao userDao;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("http://www.guoruijava.xyz").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //表示客户端订阅地址的前缀信息，也就是客户端接收服务端消息的地址的前缀信息
        registry.enableSimpleBroker("/topic");
        //指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
        registry.setApplicationDestinationPrefixes("/app");
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @EventListener
    public void connectedEvent(SessionConnectedEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("接入...");
        event.getTimestamp();
        Principal principal = event.getUser();
        Method method = principal.getClass().getDeclaredMethod("getPrincipal");
        int id = ((User)(method.invoke(event.getUser(),null))).getId();
        this.users.add(id);
        System.out.println("由用户接入连接..."+   "当前用户总数" + users.toString());
    }
    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("退出...");
        Principal principal = event.getUser();
        Method method = principal.getClass().getDeclaredMethod("getPrincipal");
        int id = ((User)(method.invoke(event.getUser(),null))).getId();
        System.out.println("退出...");
        int index=0;
        Boolean is = false;
        for (;index<users.size();index++){
            if (users.get(index) == id){
                is = true;
                break;}
        }
        if (is)
            this.users.remove(index);
        userDao.updateLastDate(new Date(),id);
        System.out.println("由用户退出连接...当前用户总数" + users.toString());
    }


}

//@Configuration
//public class WebSocketConfig {
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
//}
