package com.example.liu.weidea.service;

import com.example.liu.weidea.entity.Article;
import com.example.liu.weidea.entity.Comment;
import com.example.liu.weidea.entity.Praise;

import java.util.List;

public interface ArticleService {
    public static final int REG_MSG_OK = 0 ;
    public static final int REG_MSG_FAIL_NAMEEXISTS = 1 ;
    public static final int REG_MSG_FAIL_INFO_NON = 2 ;
    public static final int REG_MSG_FAIL_OTHER = 3 ;

    String KEY_Article = "Article" ;
    String KEY_MSG = "Msg" ;
    int LOGIN_MSG_OK = 0 ;
    int LOGIN_MSG_FAIL_NON = 1 ;
    int LOGIN_MSG_FAIL_ERROR = 2 ;
    int LOGIN_MSG_FAIL_OTHER = 3 ;
    int ROLE_ADMIN = 0 ;
    int ROLE_Article = 1 ;

    /**
     * 添加文章
     * @param article
     * @return
     */
    Integer add(Article article);

    /**
     * 更新
     * @param article
     * @return
     */
    Article update(Article article);

    /**
     * 获取所有文章
     * @return
     */
    List<Article> getAll();

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据关键字查询文章
     * @param keyword
     * @return
     */
    List<Article> getArticle(String keyword);

    /**
     * 根据id获取文章信息
     * @param id
     * @return
     */
    Article getArticleById(Integer id);

    /**
     * 根据type获取文章
     * @param id
     * @return
     */
    List<Article> getArticleByType(Integer id);

    /**
     * 获取用户文章
     * @param sender
     * @return
     */
    List<Article> getArticleByUser(Integer sender);

    /**
     * 根据id获得点赞数
     * @param id
     * @return
     */
    Integer getPNum(Integer id);

    /**
     * 根据id获得评论数
     * @param id
     * @return
     */
    Integer getCNum(Integer id);

    /**
     * 获取文章评论
     * @param id
     * @return
     */
    List<Comment> getCommentById(Integer id);

    /**
     * 取消点赞
     * @param praise
     * @return
     */
    int delP(Praise praise);

    /**
     * 点赞
     * @param praise
     * @return
     */
    Integer addP(Praise praise);

    /**
     * 是否点赞
     * @param praise
     * @return
     */
    int getIsP(Praise praise);

//    /**
//     * 根据id改变显示状态
//     * @param id
//     * @param state
//     * @return
//     */
//    Integer updateState(Integer id, String state);
}
