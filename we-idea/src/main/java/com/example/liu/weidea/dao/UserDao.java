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

    /**
     * 根据id更新内容
     * @param user
     * @return
     */
    @Update("<script>" +
            "update user " +
            "        <set> " +
            "            <if test=\"null != password\">" +
            "                password=#{password} , " +
            "            </if>\n" +
            "            <if test=\"null != name\">" +
            "                name=#{name} , " +
            "            </if>\n" +
            "            <if test=\"null != sex\"> " +
            "                sex=#{sex}, " +
            "            </if>\n" +
            "            <if test=\"null != email\"> " +
            "                email = #{email} , " +
            "            </if>\n" +
            "            <if test=\"null != phone\"> " +
            "                phone=#{phone} , " +
            "            </if>" +
            "            <if test=\"null != photourl\"> " +
            "                photourl=#{photourl}, " +
            "            </if>\n" +
            "            <if test=\"null != birthday\"> " +
            "                birthday = #{birthday} , " +
            "            </if>\n" +
            "            <if test=\"null != role\">" +
            "                role=#{role} , " +
            "            </if>\n" +
            "        </set> " +
            "        where id=#{id}"
            +"</script>")
    int updateById(User user) ;

    /**
     * 根据id查找对应用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User getUserById(Integer id) ;

    /**
     * 根据id删除对应用户信息
     * @param id
     * @return
     */
    @Delete("delete from user where id=#{id}")
    int deleteById(Integer id) ;

    /**
     * 根据关键字查询用户
     * @param keyword
     * @return
     */
     @Select("<script>" +
            "select * from user " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where (name like #{key} and role!=0) or (phone like #{key} and role!=0)" +
            "        </if>"
            +"</script>")
    List<User> getUser(String keyword) ;

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    @Select("<script>" +
            "select * from user " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where (name like #{key} and role!=0) or (phone like #{key} and role=2)" +
            "        </if>"
            +"</script>")
    List<User> getVolunteer(String keyword) ;

    /**
     * 根据关键字查询管理员
     * @param keyword
     * @return
     */
    @Select("<script>" +
            "select * from user " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where (name like #{key} and role!=0) or (phone like #{key} and role=0)" +
            "        </if>"
            +"</script>")
    List<User> getAdmin(String keyword) ;

    /**
     * 获得所有用户
     * @return
     */
    @Select("select * from user")
    List<User> getAll() ;



}
