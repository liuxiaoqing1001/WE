package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Consultants;
import com.example.liu.weidea.entity.User;
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

    /**
     * 查询审核通过的自愿者
     * @return
     */
    @Select("select * from volunteer where state='通过'")
    List<Volunteer> getV();

    /**
     * 查询审核中的自愿者
     * @return
     */
    @Select("select * from volunteer where state!='通过'")
    List<Volunteer> getNotV();

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    @Select("<script>" +
            "select * from volunteer " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where name like #{key} or realName like #{key}" +
            "        </if>"
            +"</script>")
    List<Volunteer> getVolunteer(String keyword) ;

//    /**
//     * 审核自愿者
//     * @param name
//     * @return
//     */
//    @Update("update volunteer set state='通过' where name=#{name}")
//    int updateVState(String name);
}
