package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Article;
import com.example.liu.weidea.entity.Comment;
import com.example.liu.weidea.entity.Praise;
import com.example.liu.weidea.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 添加文章
     * @param map
     * @return
     */
    @PostMapping("/add")
    public ResponseData add(@RequestBody Map<String , Object> map) {
        Article article = new Article();
        article.setSender((String) map.get("sender"));
        article.setTitle((String) map.get("title"));
        article.setContent((String) map.get("content"));
        article.setType((String) map.get("type"));
        Integer result = articleService.add(article) ;
        return new ResponseData(
                result ==0 ? 0 : 1 ,
                result ==0 ? "添加成功" : "添加失败" ,
                result
        ) ;
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @PostMapping("/update")
    public ResponseData update(@RequestBody Map<String , Object> map) {
        Article article = new Article();
        article.setId((Integer) map.get("id"));
        article.setTitle((String) map.get("title"));
        article.setContent((String) map.get("content"));
        article.setType((String) map.get("type"));
        Article m = articleService.update(article) ;
        return new ResponseData(
                m !=null ? 0 : 1 ,
                m !=null ? "更新成功" : "更新失败" ,
                m
        ) ;
    }

    /**
     * 获取所有文章
     * @return
     */
    @GetMapping("/getAll")
    public ResponseData getAll(){
        List<Article> list = articleService.getAll();
        return new ResponseData(
                list != null?0:1,
                list != null?"查询成功":"查询失败",
                list
        );
    }

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseData deleteById(@PathVariable("id") Integer id) {
        int result = articleService.deleteById(id) ;
        return  new ResponseData(
                result !=0 ? 0 : 1 ,
                result !=0 ? "删除成功" : "删除失败" ,
                result
        ) ;
    }

    /**
     * 根据关键字查询文章
     * @param keyword
     * @return
     */
    @GetMapping("/getArticle/{keyword}")
    public ResponseData getArticle(@PathVariable("keyword") String keyword) {
        List<Article> u = articleService.getArticle(keyword) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "查询成功" : "查询失败" ,
                u
        );
    }

    /**
     * 根据id获得文章信息
     * @param id
     * @return
     */
    @GetMapping("/getArticleById")
    public ResponseData getArticleById(@RequestParam(value = "id",required = false) Integer id) {
        Article u = articleService.getArticleById(id) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "获取成功" : "获取失败" ,
                u
        );
    }

    /**
     * 根据type获取文章
     * @param id
     * @return
     */
    @GetMapping("/getArticleByType")
    public ResponseData getArticleByType(@RequestParam(value = "id",required = false) Integer id) {
        List<Article> list;
        if(id!=1){
            list = articleService.getArticleByType(id);
        }else {
            list = articleService.getAll();

        }
        return new ResponseData(
                list != null?0:1,
                list != null?"获取成功":"获取失败",
                list
        );
    }

    /**
     * 获取用户文章
     * @param sender
     * @return
     */
    @GetMapping("/getArticleByUser")
    public ResponseData getArticleByUser(@RequestParam(value = "sender",required = false) Integer sender) {
        List<Article> list = articleService.getArticleByUser(sender);
        return new ResponseData(
                list != null?0:1,
                list != null?"获取成功":"获取失败",
                list
        );
    }

    /**
     * 根据id获得点赞数
     * @param id
     * @return
     */
    @GetMapping("/getPNum")
    public ResponseData getPNum(@RequestParam(value = "id",required = false) Integer id) {
        Integer pNum = articleService.getPNum(id);
        return new ResponseData(
                pNum != null?0:1,
                pNum != null?"获取成功":"获取失败",
                pNum
        );
    }

    /**
     * 根据id获得评论数
     * @param id
     * @return
     */
    @GetMapping("/getCNum")
    public ResponseData getCNum(@RequestParam(value = "id",required = false) Integer id) {
        Integer cNum = articleService.getCNum(id);
        return new ResponseData(
                cNum != null?0:1,
                cNum != null?"获取成功":"获取失败",
                cNum
        );
    }

    /**
     * 获取文章评论
     * @param id
     * @return
     */
    @GetMapping("/getCommentById")
    public ResponseData getCommentById(@RequestParam(value = "id",required = false) Integer id) {
        List<Comment> list = articleService.getCommentById(id);
        return new ResponseData(
                list != null?0:1,
                list != null?"获取成功":"获取失败",
                list
        );
    }

    /**
     * 点赞
     * @param map
     * @return
     */
    @PostMapping("/addP")
    public ResponseData addP(@RequestBody Map<String , Object> map) {
        Praise praise = new Praise();
        praise.setAid((Integer) map.get("aid"));
        praise.setUid(Integer.parseInt(map.get("uid").toString()));
        Integer result = articleService.addP(praise) ;
        return new ResponseData(
                result ==0 ? 0 : 1 ,
                result ==0 ? "添加成功" : "添加失败" ,
                result
        ) ;
    }

    /**
     * 取消点赞
     * @param aid
     * @param uid
     * @return
     */
    @DeleteMapping("/delP")
    public ResponseData delP(@RequestParam(value = "aid",required = false) Integer aid,
                             @RequestParam(value = "uid",required = false) Integer uid) {
        Praise praise = new Praise();
        praise.setAid(aid);
        praise.setUid(uid);
        int result = articleService.delP(praise) ;
        return  new ResponseData(
                result !=0 ? 0 : 1 ,
                result !=0 ? "删除成功" : "删除失败" ,
                result
        ) ;
    }

    /**
     * 是否点赞
     * @param aid
     * @param uid
     * @return
     */
    @GetMapping("/getIsP")
    public ResponseData getIsP(@RequestParam(value = "aid",required = false) Integer aid,
                               @RequestParam(value = "uid",required = false) Integer uid) {
        Praise praise = new Praise();
        praise.setAid(aid);
        praise.setUid(uid);
        int result = articleService.getIsP(praise) ;
        return  new ResponseData(
                result !=0 ? 0 : 1 ,
                result !=0 ? "获取成功" : "未点赞" ,
                result
        ) ;
    }

}
