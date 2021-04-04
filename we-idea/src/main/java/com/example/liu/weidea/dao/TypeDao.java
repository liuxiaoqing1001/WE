package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeDao {

    /**
     * 获取所有类型
     * @return
     */
    @Select("select * from type")
    List<Type> getAll();

    /**
     * 根据id获取name
     * @param id
     * @return
     */
    @Select("select name from type where id=#{id}")
    String getNameById(Integer id);

}
