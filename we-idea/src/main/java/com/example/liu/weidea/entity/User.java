package com.example.liu.weidea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private Data birthday ;
    private  String sex ;
    private String email , mobile , photourl ,status;
    private Date regdate ;
    private Integer role ;

}
