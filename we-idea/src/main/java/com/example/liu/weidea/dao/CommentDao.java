package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Article;
import com.example.liu.weidea.entity.Comment;
import com.example.liu.weidea.entity.Type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentDao {

    /**
     * 获取aid所有评论
     * @return
     */
    @Select("select * from comment where aid=#{aid}")
    List<Comment> getAllByAid(Integer aid);

    /**
     * 获取aid评论数
     * @return
     */
    @Select("select count(*) from comment where aid=#{aid}")
    Integer getCNum(Integer aid);

    /**
     * 获取aid所有评论
     * @return
     */
    @Select("select * from comment where aid=#{aid}")
    List<Comment> getCommentById(Integer aid);

    /**
     * 获取sid所有评论
     * @return
     */
    @Select("select * from comment where sid=#{sid}")
    List<Comment> getAllBySid(Integer sid);

    /**
     * 添加文章评论
     * @param comment
     * @return
     */
    @Insert("insert into comment(aid,content,sender,receiver,time) " +
            "values(#{aid},#{content},#{sender},#{receiver},now())")
    int addA(Comment comment);

    /**
     * 添加文章评论
     * @param comment
     * @return
     */
    @Insert("insert into comment(sid,content,sender,receiver,time) " +
            "values(#{sid},#{content},#{sender},#{receiver},now())")
    int addS(Comment comment);

    /**
     * 根据用户id获取消息
     * @param receiver
     * @return
     */
    @Select("select * from comment where receiver=#{receiver} order by time desc,id asc")
    List<Comment> getMsgList(String receiver);
}
