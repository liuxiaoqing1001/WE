package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Msg;
import com.example.liu.weidea.entity.User;
import com.example.liu.weidea.service.MsgService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    MsgService msgService;

    /**
     * 获取cid所有聊天记录
     * @return
     */
    @GetMapping("/getAllByCid")
    public ResponseData getAllByCid(@RequestParam(value="cid" ,required =false) Integer cid,
                                    @RequestParam(value="userId" ,required =false) Integer userId){
        List<Msg> list = msgService.getAllByCid(cid);
        for(int i=0;i<list.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("text",list.get(i).getContent());
            list.get(i).setText(jsonObject);
            if(list.get(i).getUserId().equals(userId)){
                list.get(i).setMine(true);
            }else {
                list.get(i).setMine(false);
            }
        }
        return new ResponseData(
                list != null?0:1,
                list != null?"查询成功":"查询失败",
                list
        );
    }

    /**
     * 发送消息
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping("/addMsg")
    public ResponseData addMsg(@RequestBody Map<String , Object> map) {
        String msg = "" ;
        Integer result = 1;
        try{
            JSONObject jsonMsgObj = JSONObject.parseObject(JSON.toJSONString(map.get("msgObj")));
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("other")));
            Msg msgObject = new Msg();
            msgObject.setCid(Integer.valueOf(json.getString("cid")));
            msgObject.setUserId(Integer.valueOf(json.getString("userId")));
            JSONObject jsonTxt = JSONObject.parseObject(jsonMsgObj.getString("text"));
            msgObject.setContent(jsonTxt.getString("text"));

            Date date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonMsgObj.getString("date"));
            msgObject.setDate(date);
            result = msgService.addMsg(msgObject) ;
            msg = "发送成功";
        }catch (Exception e){
            msg = "发送失败："+e;
        }
        return new ResponseData(
                result ,
                msg,
                result == 0
        ) ;
    }


}
