package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Module;
import com.example.liu.weidea.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/module")
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
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("module")));
        Module module = new Module();
        module.setName(json.getString("name"));
        module.setRoute(json.getString("route"));
        module.setText(json.getString("text"));
        Integer result = moduleService.add(module) ;
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
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("module")));
        Module module = new Module();
        module.setId(Integer.valueOf(json.getString("id")));
        module.setName(json.getString("name"));
        module.setRoute(json.getString("route"));
        module.setText(json.getString("text"));
        module.setState(json.getString("state"));
        Module m = moduleService.update(module) ;
        return new ResponseData(
                m !=null ? 0 : 1 ,
                m !=null ? "更新成功" : "更新失败" ,
                m
        ) ;
    }

    /**
     * 获取所有模块
     * @return
     */
    @GetMapping("/getAll")
    public ResponseData getAll(){
        List<Module> list = moduleService.getAll();
        return new ResponseData(
                list != null?0:1,
                list != null?"查询成功":"查询失败",
                list
        );
    }

    /**
     * 根据id删除模块
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseData deleteById(@PathVariable("id") Integer id) {
        int result = moduleService.deleteById(id) ;
        return  new ResponseData(
                result !=0 ? 0 : 1 ,
                result !=0 ? "删除成功" : "删除失败" ,
                result
        ) ;
    }

    /**
     * 根据关键字查询模块
     * @param keyword
     * @return
     */
    @GetMapping("/getModule/{keyword}")
    public ResponseData getModule(@PathVariable("keyword") String keyword) {
        List<Module> u = moduleService.getModule(keyword) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "查询成功" : "查询失败" ,
                u
        );
    }

    /**
     * 根据id获得模块信息
     * @param id
     * @return
     */
    @GetMapping("/getModuleById")
    public ResponseData getModuleById(@RequestParam(value = "id",required = false) Integer id) {
        Module u = moduleService.getModuleById(id) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "获取成功" : "获取失败" ,
                u
        );
    }

    /**
     * 根据id改变显示状态
     * @param map
     * @return
     */
    @PostMapping("/updateState")
    public ResponseData updateState(@RequestBody Map<String , Object> map){
        Integer result=moduleService.updateState((Integer) map.get("id"), (String)map.get("state"));
        return new ResponseData(
                result !=null ? 0 : 1 ,
                result !=null ? "修改成功" : "修改失败" ,
                result
        );
    }


}
