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
    @Select("select * from say " +
            "AS t1 JOIN " +
            "(select ROUND(RAND()*((select MAX(id) from say)-(select MIN(id) from say))+(select MIN(id) from say)) " +
            "AS id) AS t2 " +
            "WHERE t1.id >= t2.id ORDER BY t1.id LIMIT 1")
//    @Select("select * from say order by rand() limit 1")
    Say getRandSay();

    /**
     * 根据id获取内容
     * @param sid
     * @return
     */
    @Select("select content from say where id=#{sid}")
    String getByAid(Integer sid);
}
