package com.example.liu.weidea.service;

import com.example.liu.weidea.entity.Comment;

import java.util.List;

public interface CommentService {
    public static final int REG_MSG_OK = 0 ;
    public static final int REG_MSG_FAIL_NAMEEXISTS = 1 ;
    public static final int REG_MSG_FAIL_INFO_NON = 2 ;
    public static final int REG_MSG_FAIL_OTHER = 3 ;

    String KEY_Type = "Comment" ;
    String KEY_MSG = "Msg" ;
    int LOGIN_MSG_OK = 0 ;
    int LOGIN_MSG_FAIL_NON = 1 ;
    int LOGIN_MSG_FAIL_ERROR = 2 ;
    int LOGIN_MSG_FAIL_OTHER = 3 ;
    int ROLE_ADMIN = 0 ;
    int ROLE_Type = 1 ;

    /**
     * 添加文章评论
     * @param comment
     * @return
     */
    Integer addA(Comment comment);

    /**
     * 添加树桐消息评论
     * @param comment
     * @return
     */
    Integer addS(Comment comment);
}
