package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Consultants;
import com.example.liu.weidea.entity.Volunteer;
import org.apache.ibatis.annotations.Insert;
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
            "            <if test=\"null != state\"> " +
            "                state=#{state} , " +
            "            </if>" +
            "        </set> " +
            "        where name=#{name}"
            +"</script>")
    int updateVByName(Volunteer volunteer);

    /**
     * 提交自愿者申请
     * @param volunteer
     * @return
     */
    @Insert("insert into volunteer(name,realName,comeFrom,identity,certificate,diploma,entryTime) " +
            "values(#{name},#{realName},#{comeFrom},#{identity},#{certificate},#{diploma},now())")
    Integer addVRequest(Volunteer volunteer);

    /**
     * 申请是否存在
     * @param identity
     * @return
     */
    @Select("select count(*) from volunteer where identity=#{identity}")
    int volunteerSearch(String identity);
}
