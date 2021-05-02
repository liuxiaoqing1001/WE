package com.example.liu.weidea.service;

import com.example.liu.weidea.entity.Msg;

import java.util.List;

public interface MsgService {

    /**
     * 获取所有文章
     * @return
     */
    List<Msg> getAllByCid(Integer cid);

    /**
     * 发送消息
     * @param msgObject
     * @return
     */
    Integer addMsg(Msg msgObject);
}
