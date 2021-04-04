package com.example.liu.weidea.controller;

import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Type;
import com.example.liu.weidea.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeService typeService;

    /**
     * 获取所有文章
     * @return
     */
    @GetMapping("/getAll")
    public ResponseData getAll(){
        List<Type> list = typeService.getAll();
        return new ResponseData(
                list != null?0:1,
                list != null?"查询成功":"查询失败",
                list
        );
    }
}
