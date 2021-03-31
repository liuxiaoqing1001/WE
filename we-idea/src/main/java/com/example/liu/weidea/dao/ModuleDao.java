package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Module;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ModuleDao {

    /**
     * 根据name查找是否存在
     * @param name
     * @return
     */
    @Select("select count(*) from module where name=#{name}")
    int moduleSearch(String name);

    /**
     * 添加模块
     * @param module
     * @return
     */
    @Insert("insert into module(name,route,text,createDate) values(#{name},#{route},#{text},now())")
    int add(Module module);

    /**
     * 更新
     * @param module
     * @return
     */
    @Update("<script>" +
            "update module " +
            "        <set> " +
            "            <if test=\"null != name\">" +
            "                name=#{name} , " +
            "            </if>\n" +
            "            <if test=\"null != route\">" +
            "                route=#{route} , " +
            "            </if>\n" +
            "            <if test=\"null != text\"> " +
            "                text=#{text}, " +
            "            </if>\n" +
            "            <if test=\"null != state\"> " +
            "                state = #{state} , " +
            "            </if>\n" +
            "        </set> " +
            "        where id=#{id}"
            +"</script>")
    int updateById(Module module);

    /**
     * 根据id查找模块信息
     * @param id
     * @return
     */
    @Select("select * from module where id=#{id}")
    Module getModuleById(Integer id);

    /**
     * 获取所有模块
     * @return
     */
    @Select("select * from module")
    List<Module> getAll();

    /**
     * 根据id删除模块
     * @param id
     * @return
     */
    @Delete("delete from module where id=#{id}")
    int deleteById(Integer id);

    /**
     * 根据关键字查询模块
     * @param keyword
     * @return
     */
    @Select("<script>" +
            "select * from module " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where name like #{key} or text like #{key}" +
            "        </if>"
            +"</script>")
    List<Module> getModule(String keyword);

    /**
     * 根据id改变显示状态
     * @param id
     * @param state
     * @return
     */
    @Update("update module set state=#{state} where id=#{id}")
    Integer updateState(Integer id, String state);

//    /**
//     * 得到最大的排序
//     * @return
//     */
//    @Select("select max(sort) from module")
//    Integer getLastSort();
}
