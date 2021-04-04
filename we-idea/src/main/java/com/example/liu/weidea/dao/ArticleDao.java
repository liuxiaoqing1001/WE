package com.example.liu.weidea.dao;

import com.example.liu.weidea.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    /**
     * 添加文章
     * @param article
     * @return
     */
    @Insert("insert into article(sender,title,content,type,sendDate) " +
            "values(#{sender},#{title},#{content},#{type},now())")
    int add(Article article);

    /**
     * 更新
     * @param article
     * @return
     */
    @Update("<script>" +
            "update article " +
            "        <set> " +
            "            <if test=\"null != sender\">" +
            "                sender=#{sender} , " +
            "            </if>\n" +
            "            <if test=\"null != title\">" +
            "                title=#{title} , " +
            "            </if>\n" +
            "            <if test=\"null != content\">" +
            "                content=#{content} , " +
            "            </if>\n" +
            "            <if test=\"null != type\">" +
            "                type=#{type} , " +
            "            </if>\n" +
            "            <if test=\"null != imgUrl\">" +
            "                imgUrl=#{imgUrl} , " +
            "            </if>\n" +
            "        </set> " +
            "        where id=#{id}"
            +"</script>")
    int updateById(Article article);

    /**
     * 根据id查找文章信息
     * @param id
     * @return
     */
    @Select("select * from article where id=#{id}")
    Article getArticleById(Integer id);

    /**
     * 获取所有文章
     * @return
     */
    @Select("select * from article")
    List<Article> getAll();

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    @Delete("delete from article where id=#{id}")
    int deleteById(Integer id);

    /**
     * 根据关键字查询文章
     * @param keyword
     * @return
     */
    @Select("<script>" +
            "select * from article " +
            "        <if test=\"keyword != null\"> " +
            "            <bind name=\"key\" value=\"'%'+keyword+'%'\"/> " +
            "            where sender like #{key} or title like #{key} or content like #{key} or type like #{key}" +
            "        </if>"
            +"</script>")
    List<Article> getArticle(String keyword);

    /**
     * 根据type获取文章
     * @param type
     * @return
     */
    @Select("select * from article where type=#{type}")
    List<Article> getArticleByType(String type);

    /**
     * 获取用户文章
     * @param sender
     * @return
     */
    @Select("select * from article where sender=#{sender}")
    List<Article> getArticleByUser(Integer sender);

//    /**
//     * 根据id改变显示状态
//     * @param id
//     * @param state
//     * @return
//     */
//    @Update("update article set state=#{state} where id=#{id}")
//    Integer updateState(Integer id, String state);
}
