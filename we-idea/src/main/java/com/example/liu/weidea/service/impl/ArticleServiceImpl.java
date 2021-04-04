package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.ArticleDao;
import com.example.liu.weidea.entity.Article;
import com.example.liu.weidea.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;
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
        // 先进行账号是否存在检测
        int count = articleDao.articleSearch(article.getSender()) ;
        if(count > 0) {
            return REG_MSG_FAIL_NAMEEXISTS ;
        }
        article.setSender(article.getSender());
        article.setContent(article.getContent());
        article.setTitle(article.getTitle());
        article.setType(article.getType());
//        Integer sort = articleDao.getLastSort();
//        article.setSort(sort);
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
        if(article.getSender() == null) {
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
        return articleDao.getAll();
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
