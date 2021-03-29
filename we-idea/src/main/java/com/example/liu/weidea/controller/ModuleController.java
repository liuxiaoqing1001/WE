package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Module;
import com.example.liu.weidea.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/Module")
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    /**
     * 添加模块
     * @param map
     * @return
     */
    @PostMapping("/add")
    public ResponseData add(@RequestBody Map<String , Object> map) {
        Module module = new Module() ;
        module.setName((String)map.get("name"));
        module.setRoute((String)map.get("route"));
        module.setText((String)map.get("text"));
        Integer result = moduleService.add(module) ;
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

    /**
     * 更新
     * @param map
     * @return
     */
    @PostMapping("/update")
    public ResponseData update(@RequestBody Map<String , Object> map) {
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("module")));
        Module module = new Module();
        module.setId(Integer.valueOf(json.getString("id")));
        module.setName(json.getString("name"));
        module.setRoute(json.getString("route"));
        module.setText(json.getString("text"));
        module.setSort(Integer.valueOf(json.getString("sort")));
        Module m = moduleService.update(module) ;
        return new ResponseData(
                m !=null ? 0 : 1 ,
                m !=null ? "更新成功" : "更新失败" ,
                m
        ) ;
    }
    
}
