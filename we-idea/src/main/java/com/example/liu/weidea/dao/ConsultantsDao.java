package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Consultants;
import com.example.liu.weidea.entity.Type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    List<Consultants> getConsultantsByVid(Integer vid);

    /**
     * 获取咨询者
     * @param id
     * @return
     */
    @Select("select * from consultants where id=#{id}")
    Consultants getById(Integer id);

    /**
     * 根据id修改咨询者状态
     * @param state
     * @param id
     * @return
     */
    @Update("update consultants set state=#{state} where id=#{id}")
    int updateStateById(String state,Integer id);

    /**
     * 匹配咨询师
     * @param vid
     * @param uid
     * @return
     */
    @Insert("insert into consultants(vid,uid,time) values(#{vid},#{uid},now())")
    Integer addConsultants(Integer vid, Integer uid);

    @Select("select last_insert_id();")
    Integer last_insert_id();
}
