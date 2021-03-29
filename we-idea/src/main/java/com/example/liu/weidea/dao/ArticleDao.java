package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    /**
     * 文章是否存在
     * @param phone
     * @return
     */
    @Select("select count(*) from Article where phone=#{phone}")
    int ArticleSearch(String phone) ;

    /**
     * 根据手机号和密码查找文章
     * @param phone
     * @param password
     * @return
     */
    @Select("select * from Article where phone=#{phone} and password=#{pwd}")
    Article select(@Param("phone") String phone, @Param("pwd") String password) ;

    /**
     * 注册
     * @param Article
     * @return
     */
    @Insert("insert into Article(phone,password,regdate) values(#{phone},#{password},now())")
    int add(Article Article) ;

    /**
     * 添加管理员
     * @param Article
     * @return
     */
    @Insert("insert into Article(phone,password,regdate,role) values(#{phone},#{password},now(),0)")
    int addAdmin(Article Article) ;

    /**
     * 查询数据库中所有管理员
     * @return
     */
    @Select("select * from Article where role=0")
    List<Article> getAllAdmin() ;

    /**
     * 查询数据库中所有文章（除管理员）
     * @return
     */
    @Select("select * from Article where role!=0")
    List<Article> getAllArticle() ;

    /**
     * 查询数据库中所有自愿者
     * @return
     */
    @Select("select * from Article where role=2")
    List<Article> getAllVolunteer() ;

    /**
     * 根据电话号查询文章信息
     * @param phone
     * @return
     */
    @Select("select * from Article where phone=#{phone}")
    Article searchByPhone(@Param("phone") String phone);

    /**
     * 修改密码
     * @param password
     * @param phone
     * @return
     */
    @Update("update Article set password=#{password} where phone=#{phone}")
    int modifyPwd(@Param("password") String password, @Param("phone") String phone) ;

    /**
     * 根据id更新内容
     * @param Article
     * @return
     */
    @Update("<script>" +
            "update Article " +
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
    int updateById(Article Article) ;

    /**
     * 根据id查找对应文章信息
     * @param id
     * @return
     */
    @Select("select * from Article where id=#{id}")
    Article getArticleById(Integer id) ;

    /**
     * 根据id删除对应文章信息
     * @param id
     * @return
     */
    @Delete("delete from Article where id=#{id}")
    int deleteById(Integer id) ;

    /**
     * 根据关键字查询文章
     * @param keyword
     * @return
     */
     @Select("<script>" +
            "select * from Article " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where (name like #{key} and role!=0) or (phone like #{key} and role!=0)" +
            "        </if>"
            +"</script>")
    List<Article> getArticle(String keyword) ;

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    @Select("<script>" +
            "select * from Article " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where (name like #{key} and role!=0) or (phone like #{key} and role=2)" +
            "        </if>"
            +"</script>")
    List<Article> getVolunteer(String keyword) ;

    /**
     * 根据关键字查询管理员
     * @param keyword
     * @return
     */
    @Select("<script>" +
            "select * from Article " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where (name like #{key} and role!=0) or (phone like #{key} and role=0)" +
            "        </if>"
            +"</script>")
    List<Article> getAdmin(String keyword) ;













    @Select("select * from Article")
    List<Article> getAll() ;





    //分页
    @Select("<script>" +
            "select * from Article" +
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
    List<Article> getMoreBy(@Param("id") Integer id, @Param("name") String name, @Param("regdate") String regdate, @Param("offset") Integer offset, @Param("rows") Integer rows) ;

    @Select("<script>" +
            "select count(*) from Article" +
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

    @Update("update Article set role=1 where id=#{id}")
    int upRole(@Param("id") Integer id);

    @Delete("delete from Article where id=#{id}")
    int delById(Integer id);

    @Select("select count(*) from Article where role=0")
    Integer getCount();

    @Select("select * from Article where role=0 order by id asc limit #{offset},#{rows}")
    List<Article> getLimit(@Param("offset") Integer offset, @Param("rows") Integer rows);

    @Insert("insert into Article(name,password,age,sex,email,mobile,photourl,role,regdate) " +
            "values(#{name},#{password},#{age},#{sex},#{email},#{mobile},#{photourl},0,now())")
    int addRole(Article Article);

    @Select("select photourl from Article where name=#{name}")
    String getPhotoUrl(String name);



}
