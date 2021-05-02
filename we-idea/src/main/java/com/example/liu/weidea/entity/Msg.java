package com.example.liu.weidea.entity;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(description = "websocket消息内容")
public class Msg {

//    @ApiModelProperty(value = "用户id")
    private Integer userId;

//    @ApiModelProperty(value = "用户名")
    private String username;

//    @ApiModelProperty(value = "用户头像")
    private String avatar;

//    @ApiModelProperty(value = "消息")
    private String msg;

//    @ApiModelProperty(value = "在线人数")
    private int count;

    private Integer id;
    private Integer cid;//consultantsId
    private String content;
    private JSONObject text;
    private boolean mine;
//    private String name;
//    private String sender;
//    private String receiver;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Shanghai")
    private Date date ;

}