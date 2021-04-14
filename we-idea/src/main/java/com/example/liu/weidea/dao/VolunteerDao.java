package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Consultants;
import com.example.liu.weidea.entity.Volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VolunteerDao {

    /**
     * 获取所有
     * @return
     */
    @Select("select * from volunteer")
    List<Volunteer> getAll();

    /**
     * 根据name获取
     * @param name
     * @return
     */
    @Select("select * from volunteer where name=#{name}")
    Volunteer getVolunteerByName(String name);

}
