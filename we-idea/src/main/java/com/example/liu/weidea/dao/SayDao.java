package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Say;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SayDao {

    /**
     * 添加
     * @param say
     * @return
     */
    @Insert("insert into say(sender,content,sendDate) " +
            "values(#{sender},#{content},now())")
    int add(Say say);

    /**
     * 根据id查找信息
     * @param id
     * @return
     */
    @Select("select * from say where id=#{id}")
    Say getSayById(Integer id);

    /**
     * （树洞）随机获取say
     * @return
     */
    @Select("select * from say")
    Say getRandSay();
}
