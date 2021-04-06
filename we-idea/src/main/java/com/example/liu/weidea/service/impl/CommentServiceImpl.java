package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.CommentDao;
import com.example.liu.weidea.entity.Comment;
import com.example.liu.weidea.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    /**
     * 
     * @param comment
     * @return
     */
    @Override
    public Integer addA(Comment comment) {
        if(null == comment) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        comment.setAid(comment.getAid());
        comment.setContent(comment.getContent());
        comment.setSender(comment.getSender());
        comment.setReceiver(comment.getReceiver());
        int r = commentDao.addA(comment) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }

    /**
     * 
     * @param comment
     * @return
     */
    @Override
    public Integer addS(Comment comment) {
        if(null == comment) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        comment.setSid(comment.getSid());
        comment.setContent(comment.getContent());
        comment.setSender(comment.getSender());
        comment.setReceiver(comment.getReceiver());
        int r = commentDao.addS(comment) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }
}
