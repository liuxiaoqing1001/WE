package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Module;
import com.example.liu.weidea.entity.Question;
import com.example.liu.weidea.entity.Test;
import com.example.liu.weidea.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    /**
     * 随机获取10题
     * @return
     */
    @GetMapping("/getRand")
    public ResponseData getRand(){
        List<Question> lists = testService.getRand();
        for(int i=0;i<lists.size();i++){
            List<String> strList = Arrays.asList(lists.get(i).getItem().split(";"));
            lists.get(i).setItems(strList);
            lists.get(i).setId(i+1);
            System.out.println(lists.get(i));
        }
        return new ResponseData(
                lists != null?0:1,
                lists != null?"随机获取成功":"随机获取失败",
                lists
        );
    }

    /**
     * 检测是否曾做过基础测试
     * @param uid
     * @return
     */
    @GetMapping("/getByUid")
    public ResponseData getByUid(@RequestParam(value="uid" ,required =false) Integer uid){
        Test list = testService.getByUid(uid);
        return new ResponseData(
                list != null?0:1,
                list != null?"查询成功":"查询失败",
                list
        );
    }

    /**
     * 根据id获取测试情况
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public ResponseData getById(@RequestParam(value="id" ,required =false) Integer id){
        List<Question> list = testService.getById(id);
        return new ResponseData(
                list != null?0:1,
                list != null?"查询成功":"查询失败",
                list
        );
    }

    /**
     * 匹配咨询师
     * @param map
     * @return
     */
    @PostMapping("/addConsultants")
    public ResponseData addConsultants(@RequestBody Map<String , Object> map) {
        String msg = "" ;
        Integer result = 0;
        Integer last_insert_id =0;
        try{
            Integer uid = Integer.parseInt(map.get("uid").toString());
            last_insert_id = testService.addConsultants(uid);
            msg = "匹配成功";
        }catch (Exception e){
            result = 1;
            msg = "匹配失败："+e;
        }
        return new ResponseData(
                result ,
                msg,
                last_insert_id
        ) ;
    }

    /**
     * 保存测试
     * @param map
     * @return
     */
    @PostMapping("/addTest")
    public ResponseData addTest(@RequestBody Map<String , Object> map) {
        String msg = "" ;
        Integer result;
        try{
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("testObj")));
            Test test = new Test();
            test.setUid(Integer.parseInt(json.getString("uid")));
            test.setCid(Integer.parseInt(json.getString("cid")));
            test.setQIds(json.getString("qIds"));
            test.setItem(json.getString("item"));
            test.setResult(json.getString("result"));
            result = testService.addTest(test) ;
            msg = "成功保存测试";
        }catch (Exception e){
            result = 0;
            msg = "保存测试失败："+e;
        }
        return new ResponseData(
                result ,
                msg,
                result == 0
        ) ;
    }

    /**
     * 获得题详情
     * @param qid
     * @return
     */
    @GetMapping("/getQuestionByQid")
    public ResponseData getQuestionByQid(@RequestParam(value = "qid",required = false) Integer qid) {
        Question u = testService.getQuestionByQid(qid) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "获取成功" : "获取失败" ,
                u
        );
    }

    /**
     * 删除题
     * @param qid
     * @return
     */
    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseData deleteQuestion(@PathVariable("id") Integer qid) {
        int result = testService.deleteQuestion(qid) ;
        return  new ResponseData(
                result !=0 ? 0 : 1 ,
                result !=0 ? "删除成功" : "删除失败" ,
                result
        ) ;
    }

    /**
     * 添加题
     * @param map
     * @return
     */
    @PostMapping("/addQuestion")
    public ResponseData addQuestion(@RequestBody Map<String , Object> map) {
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("question")));
        Question question = new Question();
        question.setTitle(json.getString("title"));
        question.setItem(json.getString("item"));
//        question.setAnswer(json.getString("text"));
        Integer result = testService.addQuestion(question) ;
        return new ResponseData(
                result ==1 ? 0 : 1 ,
                result ==1 ? "添加成功" : "添加失败" ,
                result
        ) ;
    }

    /**
     * 获得所有题
     * @return
     */
    @GetMapping("/getAllQuestion")
    public ResponseData getAllQuestion(){
        List<Question> list = testService.getAllQuestion();
        return new ResponseData(
                list != null?0:1,
                list != null?"查询成功":"查询失败",
                list
        );
    }

}
