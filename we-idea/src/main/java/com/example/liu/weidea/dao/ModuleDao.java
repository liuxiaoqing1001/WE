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
            "            <if test=\"null != sort\"> " +
            "                sort = #{sort} , " +
            "            </if>\n" +
            "        </set> " +
            "        where id=#{id}"
            +"</script>")
    int updateById(Module module);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Select("select * from module where id=#{id}")
    Module getModuleById(Integer id);
}
