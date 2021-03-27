package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 用户是否存在
     * @param phone
     * @return
     */
    @Select("select count(*) from user where phone=#{phone}")
    int userSearch(String phone) ;

    /**
     * 根据手机号和密码查找用户
     * @param phone
     * @param password
     * @return
     */
    @Select("select * from user where phone=#{phone} and password=#{pwd}")
    User select(@Param("phone") String phone, @Param("pwd") String password) ;

    /**
     * 注册
     * @param user
     * @return
     */
    @Insert("insert into user(phone,password,regdate) values(#{phone},#{password},now())")
    int add(User user) ;

    /**
     * 添加管理员
     * @param user
     * @return
     */
    @Insert("insert into user(phone,password,regdate,role) values(#{phone},#{password},now(),0)")
    int addAdmin(User user) ;

    /**
     * 查询数据库中所有管理员
     * @return
     */
    @Select("select * from user where role=0")
    List<User> getAllAdmin() ;

    /**
     * 查询数据库中所有用户（除管理员）
     * @return
     */
    @Select("select * from user where role!=0")
    List<User> getAllUser() ;

    /**
     * 查询数据库中所有自愿者
     * @return
     */
    @Select("select * from user where role=2")
    List<User> getAllVolunteer() ;

    /**
     * 根据电话号查询用户信息
     * @param phone
     * @return
     */
    @Select("select * from user where phone=#{phone}")
    User searchByPhone(@Param("phone") String phone);

    /**
     * 修改密码
     * @param password
     * @param phone
     * @return
     */
    @Update("update user set password=#{password} where phone=#{phone}")
    int modifyPwd(@Param("password") String password, @Param("phone") String phone) ;











    @Select("select * from user")
    List<User> getAll() ;

    //根据id更新内容
    @Update("<script>" +
            "update user " +
            "        <set> " +
            "            <if test=\"null != password\">" +
            "                password=#{password} , " +
            "            </if>\n" +
            "            <if test=\"null != age\">" +
            "                age=#{age} , " +
            "            </if>\n" +
            "            <if test=\"null != sex\"> " +
            "                sex=#{sex}, " +
            "            </if>\n" +
            "            <if test=\"null != email\"> " +
            "                email = #{email} , " +
            "            </if>\n" +
            "            <if test=\"null != mobile\"> " +
            "                mobile=#{mobile} , " +
            "            </if>" +
            "            <if test=\"null != photourl\"> " +
            "                photourl=#{photourl}, " +
            "            </if>\n" +
            "            <if test=\"null != status\"> " +
            "                status = #{status} , " +
            "            </if>\n" +
            "            <if test=\"null != role\">" +
            "                role=#{role} , " +
            "            </if>\n" +
            "        </set> " +
            "        where id=#{id}"
            +"</script>")
    int updateById(User user) ;

    //根据id查找对应用户信息
    @Select("select * from user where id=#{id}")
    User getById(Integer id) ;

    //分页
    @Select("<script>" +
            "select * from user" +
            "        <where>" +
            "            <if test=\"null != id\">" +
            "                and id=#{id}" +
            "            </if>" +
            "            <if test=\"null != name\">" +
            "                    and name = #{name}" +
            "            </if>" +
            "            <if test=\"null != regdate\">" +
            "                and DATE_FORMAT(regdate,'%Y%m%d')=#{regdate}" +
            "            </if>" +
            "        </where>"+
            " order by regdate desc limit #{offset},#{rows}" +
            "</script>")
    List<User> getMoreBy(@Param("id") Integer id, @Param("name") String name, @Param("regdate") String regdate, @Param("offset") Integer offset, @Param("rows") Integer rows) ;

    @Select("<script>" +
            "select count(*) from user" +
            "        <where>" +
            "            <if test=\"null != id\">" +
            "                and id=#{id}" +
            "            </if>" +
            "            <if test=\"null != name\">" +
            "                    and name = #{name}" +
            "            </if>" +
            "            <if test=\"null != regdate\">" +
            "                and DATE_FORMAT(regdate,'%Y%m%d')=#{regdate}" +
            "            </if>" +
            "        </where>"+
            "</script>")
    int getMoreCount(@Param("id") Integer id, @Param("name") String name, @Param("regdate") String regdate) ;


    //根据id删除对应用户信息
    @Delete("delete from user where id=#{id}")
    int DeleteById(Integer id) ;

    @Update("update user set role=1 where id=#{id}")
    int upRole(@Param("id") Integer id);

    @Delete("delete from user where id=#{id}")
    int delById(Integer id);

    @Select("select count(*) from user where role=0")
    Integer getCount();

    @Select("select * from user where role=0 order by id asc limit #{offset},#{rows}")
    List<User> getLimit(@Param("offset") Integer offset, @Param("rows") Integer rows);

    @Insert("insert into user(name,password,age,sex,email,mobile,photourl,role,regdate) " +
            "values(#{name},#{password},#{age},#{sex},#{email},#{mobile},#{photourl},0,now())")
    int addRole(User user);

    @Select("select photourl from user where name=#{name}")
    String getPhotoUrl(String name);


}
