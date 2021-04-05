package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectDao {

    /**
     * 根据aid获取收藏数
     * @param id
     * @return
     */
    @Select("select count(*) from collect where aid=#{aid}")
    Integer getByAid(Integer id);

    /**
     * 根据uid判断是否收藏
     * @param uid
     * @return
     */
    @Select("select count(*) from collect where uid=#{uid}")
    Integer getByUid(Integer uid);

    /**
     * 根据uid获取所有
     * @param uid
     * @return
     */
    @Select("select * from collect where uid=#{uid}")
    Integer getAllByUid(Integer uid);
}
