package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Consultants;
import com.example.liu.weidea.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConsultantsDao {

    /**
     * 获取所有
     * @return
     */
    @Select("select * from consultants")
    List<Consultants> getAll();

    /**
     * 根据vid获取
     * @param vid
     * @return
     */
    @Select("select * from consultants where vid=#{vid}")
    List<Consultants> getConsultantsById(Integer vid);

}
