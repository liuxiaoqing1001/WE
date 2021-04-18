package com.example.liu.weidea.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private Integer id;
    private Integer aid;//文章
    private Integer sid;//树洞
    private String content;
    private String sender;
    private String receiver;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Shanghai")
    private Date time ;
    private String msgContent;
}