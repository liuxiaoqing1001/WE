package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.MsgDao;
import com.example.liu.weidea.entity.Msg;
import com.example.liu.weidea.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    MsgDao msgDao;

    /**
     * 获取所有类型
     * @return
     */
    @Override
    public List<Msg> getAllByCid(Integer cid) {
        return msgDao.getAllByCid(cid);
    }

    /**
     * 发送消息
     * @param msgObject
     * @return
     */
    @Override
    public Integer addMsg(Msg msgObject) {
        return msgDao.addMsg(msgObject);
    }
}
