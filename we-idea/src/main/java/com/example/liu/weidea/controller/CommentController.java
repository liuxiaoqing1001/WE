package com.example.liu.weidea.controller;

import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Article;
import com.example.liu.weidea.entity.Comment;
import com.example.liu.weidea.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 添加文章评论
     * @param map
     * @return
     */
    @PostMapping("/addA")
    public ResponseData addA(@RequestBody Map<String , Object> map) {
        Comment comment = new Comment();
        comment.setAid((Integer) map.get("aid"));
        comment.setContent((String) map.get("content"));
        comment.setSender((String) map.get("sender"));
        comment.setReceiver((String) map.get("receiver"));
        Integer result = commentService.addA(comment) ;
        return new ResponseData(
                result ==0 ? 0 : 1 ,
                result ==0 ? "添加成功" : "添加失败" ,
                result
        ) ;
    }

    /**
     * 添加树桐消息评论
     * @param map
     * @return
     */
    @PostMapping("/addS")
    public ResponseData addS(@RequestBody Map<String , Object> map) {
        Comment comment = new Comment();
        comment.setSid((Integer) map.get("sid"));
        comment.setContent((String) map.get("content"));
        comment.setSender((String) map.get("sender"));
        comment.setReceiver((String) map.get("receiver"));
        Integer result = commentService.addS(comment) ;
        return new ResponseData(
                result ==0 ? 0 : 1 ,
                result ==0 ? "添加成功" : "添加失败" ,
                result
        ) ;
    }
}
