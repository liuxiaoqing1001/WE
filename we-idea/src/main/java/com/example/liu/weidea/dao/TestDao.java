package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestDao {

    /**
     * 检测是否曾做过基础测试
     * @param uid
     * @return
     */
    @Select("select * from test where uid=#{uid} order by date desc limit 1")
    Test getByUid(Integer uid);

    /**
     * 保存测试
     * @param test
     * @return
     */
    @Insert("insert into test(uid,cid,qIds,item,result,date) values(#{uid},#{cid},#{qIds},#{item},#{result},now())")
    Integer addTest(Test test);

    /**
     * 根据id获取测试情况
     * @param id
     * @return
     */
    @Select("select * from test where id=#{id}")
    Test getById(Integer id);
}
