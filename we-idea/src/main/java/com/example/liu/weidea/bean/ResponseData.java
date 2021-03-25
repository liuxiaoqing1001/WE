package com.example.liu.weidea.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData implements Serializable {
    private Integer errorCode ;
    private String msg ;
    private Object data ;
}
