package com.example.liu.weidea.controller;

import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Module;
import com.example.liu.weidea.service.ModuleService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/Module")
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    @PostMapping("/add")
    public ResponseData add(@RequestBody Map<String , Object> map) {
        Module module = new Module() ;
        module.setName((String)map.get("name"));
        module.setPassword((String)map.get("route"));
        module.setPhone((String)map.get("text"));
        new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").parse(json.getString("birthday"))
        module.setPassword((String)map.get("createDate"));
        module.setPhone((String)map.get("sort"));
        Integer result = ModuleService.add(module) ;
        String msg = "" ;
        switch (result) {
            case ModuleService.REG_MSG_OK :
                msg = "添加成功" ;
                break;
            case ModuleService.REG_MSG_FAIL_NAMEEXISTS :
                msg = "模块已存在" ;
                break;
            case ModuleService.REG_MSG_FAIL_INFO_NON:
                msg = "信息不完整" ;
                break;
            default :
                msg = "添加失败" ;
                break;
        }
        return new ResponseData(
                result ,
                msg,
                result == 0
        ) ;
    }
    
}
