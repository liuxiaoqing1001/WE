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
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日" , timezone = "Asia/Shanghai")
    private Date birthday ;
    private  String sex ;
    private String email , phone, photourl;
    @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss" , timezone = "Asia/Shanghai")
    private Date regdate ;
    private Integer role ;

}
