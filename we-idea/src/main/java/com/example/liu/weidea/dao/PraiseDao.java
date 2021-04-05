package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Praise;
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
     * 根据uid获取
     * @param uid
     * @return
     */
    @Select("select count(*) from collect where uid=#{uid}")
    Integer getByUid(Integer uid);

}
