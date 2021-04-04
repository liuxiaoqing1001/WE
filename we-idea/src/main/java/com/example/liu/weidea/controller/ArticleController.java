package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Article;
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
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("article")));
        Article article = new Article();
        article.setSender(json.getString("sender"));
        article.setContent(json.getString("title"));
        article.setContent(json.getString("content"));
        article.setContent(json.getString("type"));
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
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("article")));
        Article article = new Article();
        article.setId(Integer.valueOf(json.getString("id")));
        article.setSender(json.getString("sender"));
        article.setContent(json.getString("title"));
        article.setContent(json.getString("content"));
        article.setContent(json.getString("type"));
        article.setContent(json.getString("imgUrl"));
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

//    /**
//     * 根据id改变显示状态
//     * @param map
//     * @return
//     */
//    @PostMapping("/updateState")
//    public ResponseData updateState(@RequestBody Map<String , Object> map){
//        Integer result=articleService.updateState((Integer) map.get("id"), (String)map.get("state"));
//        return new ResponseData(
//                result !=null ? 0 : 1 ,
//                result !=null ? "修改成功" : "修改失败" ,
//                result
//        );
//    }
}
