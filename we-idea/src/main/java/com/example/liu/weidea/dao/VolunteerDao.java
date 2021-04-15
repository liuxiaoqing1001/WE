package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Consultants;
import com.example.liu.weidea.entity.Volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据id更新内容
     * @param volunteer
     * @return
     */
    @Update("<script>" +
            "update volunteer " +
            "        <set> " +
            "            <if test=\"null != realName\">" +
            "                realName=#{realName} , " +
            "            </if>\n" +
            "            <if test=\"null != comeFrom\">" +
            "                comeFrom=#{comeFrom} , " +
            "            </if>\n" +
            "            <if test=\"null != identity\"> " +
            "                identity=#{identity}, " +
            "            </if>\n" +
            "            <if test=\"null != certificate\"> " +
            "                certificate = #{certificate} , " +
            "            </if>\n" +
            "            <if test=\"null != diploma\"> " +
            "                diploma=#{diploma} , " +
            "            </if>" +
            "        </set> " +
            "        where name=#{name}"
            +"</script>")
    int updateVByName(Volunteer volunteer);
}
