package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.*;
import com.example.liu.weidea.entity.*;
import com.example.liu.weidea.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Autowired
    TypeDao typeDao;

    @Autowired
    PraiseDao praiseDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    UserDao userDao;

    /**
     * 添加文章
     * @param article
     * @return
     */
    @Override
    public Integer add(Article article) {
        if(null == article) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        article.setSender(article.getSender());
        article.setContent(article.getContent());
        article.setTitle(article.getTitle());
        article.setType(article.getType());
        int r = articleDao.add(article) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }

    /**
     * 更新
     * @param article
     * @return
     */
    @Override
    public Article update(Article article) {
        if(null == article) {
            return null ;
        }
        int r = articleDao.updateById(article) ;
        if(r != 1) {
            return null ;
        } else {
            return articleDao.getArticleById(article.getId()) ;
        }
    }

    /**
     * 获取所有文章
     * @return
     */
    @Override
    public List<Article> getAll() {
        List<Article> list= articleDao.getAll();
        return getArticlesSenderName(list);
    }

    private List<Article> getArticlesSenderName(List<Article> list) {
        for (int i=0;i<list.size();i++){
            User user=userDao.getUserById(Integer.valueOf(list.get(i).getSender()));
            String name = user.getName();
            if(name.equals("null")){
                name = "佚名";
            }
            list.get(i).setSenderName(name);
        }
        return list;
    }

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return articleDao.deleteById(id);
    }

    /**
     * 根据关键字查询文章
     * @param keyword
     * @return
     */
    @Override
    public List<Article> getArticle(String keyword) {
        if(keyword==""||keyword==null){
            return articleDao.getAll();
        }
        return articleDao.getArticle(keyword);
    }

    /**
     * 根据id获取文章信息
     * @param id
     * @return
     */
    @Override
    public Article getArticleById(Integer id) {
        return articleDao.getArticleById(id);
    }

    /**
     * 根据type获取文章
     * @param id
     * @return
     */
    @Override
    public List<Article> getArticleByType(Integer id) {
        String type = typeDao.getNameById(id);
        List<Article> list= articleDao.getArticleByType(type);
        return getArticlesSenderName(list);
    }

    /**
     * 获取用户文章
     * @param sender
     * @return
     */
    @Override
    public List<Article> getArticleByUser(Integer sender) {
        return articleDao.getArticleByUser(sender);
    }

    /**
     * 根据id获得点赞数
     * @param id
     * @return
     */
    @Override
    public Integer getPNum(Integer id) {
        return praiseDao.getPNum(id);
    }

    /**
     * 根据id获得评论数
     * @param id
     * @return
     */
    @Override
    public Integer getCNum(Integer id) {
        return commentDao.getCNum(id);
    }

    /**
     * 获取文章评论
     * @param id
     * @return
     */
    @Override
    public List<Comment> getCommentById(Integer id) {
        return commentDao.getCommentById(id);
    }

    /**
     * 取消点赞
     * @param praise
     * @return
     */
    @Override
    public int delP(Praise praise) {
        if(null == praise) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        praise.setAid(praise.getAid());
        praise.setUid(praise.getUid());
        return praiseDao.delP(praise);
    }

    /**
     * 点赞
     * @param praise
     * @return
     */
    @Override
    public Integer addP(Praise praise) {
        if(null == praise) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        praise.setAid(praise.getAid());
        praise.setUid(praise.getUid());
        int r = praiseDao.addP(praise) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }

    /**
     * 是否点赞
     * @param praise
     * @return
     */
    @Override
    public int getIsP(Praise praise) {
        if(null == praise) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        praise.setAid(praise.getAid());
        praise.setUid(praise.getUid());
        return praiseDao.getIsP(praise);
    }

    /**
     * 获取最后一条数据
     * @return
     */
    @Override
    public String getLastArticleData() {
        return articleDao.getLastArticleData();
    }

    /**
     * 根据时间查询
     * @param time
     * @return
     */
    @Override
    public List<Article> getArticleByTime(String time) {
        return articleDao.getArticleByTime(time);
    }

    /**
     * 随机获取
     * @return
     */
    @Override
    public Integer getRandArticle() {
        return articleDao.getRandArticle();
    }

//    /**
//     * 根据id改变显示状态
//     * @param id
//     * @param state
//     * @return
//     */
//    @Override
//    public Integer updateState(Integer id, String state) {
//        return articleDao.updateState(id, state);
//    }
}
