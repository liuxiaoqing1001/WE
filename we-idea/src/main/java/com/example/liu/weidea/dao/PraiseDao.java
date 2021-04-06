package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Article;
import com.example.liu.weidea.entity.Praise;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PraiseDao {

    /**
     * 获取所有
     * @return
     */
    @Select("select * from praise")
    List<Praise> getAll();

    /**
     * 根据aid获取点赞数
     * @param aid
     * @return
     */
    @Select("select count(*) from praise where aid=#{aid}")
    Integer getPNum(Integer aid);

    /**
     * 是否点赞
     * @param praise
     * @return
     */
    @Select("select count(*) from praise where aid=#{aid} and uid=#{uid}")
    Integer getIsP(Praise praise);

    /**
     * 点赞
     * @param praise
     * @return
     */
    @Insert("insert into praise(aid,uid,time) values(#{aid},#{uid},now())")
    int addP(Praise praise);

    /**
     * 取消点赞
     * @param praise
     * @return
     */
    @Delete("delete from praise where aid=#{aid} and uid=#{uid}")
    int delP(Praise praise);



}
