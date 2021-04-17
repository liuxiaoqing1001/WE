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

//    /**
//     * 获取所有
//     * @return
//     */
//    @Select("select * from say")
//    List<Say> getAll();
//
//    /**
//     * 根据id删除
//     * @param id
//     * @return
//     */
//    @Delete("delete from say where id=#{id}")
//    int deleteById(Integer id);
//
//    /**
//     * 根据关键字查询
//     * @param keyword
//     * @return
//     */
//    @Select("<script>" +
//            "select * from say " +
//            "        <if test=\"keyword != null\"> " +
//            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
//            "            where sender like #{key} or title like #{key} or content like #{key} or type like #{key}" +
//            "        </if>"
//            +"</script>")
//    List<Say> getSay(String keyword);
//
//    /**
//     * 获取
//     * @param sender
//     * @return
//     */
//    @Select("select * from say where sender=#{sender}")
//    List<Say> getsayByUser(Integer sender);
    
}
