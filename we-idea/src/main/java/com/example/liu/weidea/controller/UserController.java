package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.*;
import com.example.liu.weidea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.isoft.video.util.FileUtil;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService ;

    /**
     * 登录
     * @param phone
     * @param password
     * @return
     */
    @GetMapping("/login")
    public ResponseData login(@RequestParam(value="phone" ,required =false) String phone,
                              @RequestParam(value="password" ,required =false) String password) {
        // 调用service中方法进行login处理
        Map<String , Object> map = userService.loginCheck(phone , password) ;
        String msg = "" ;
        Integer status = (Integer)(map.get(UserService.KEY_MSG)) ;
        switch (status) {
            case UserService.LOGIN_MSG_OK :
                msg = "登录成功" ;
                break;
            case UserService.LOGIN_MSG_FAIL_ERROR :
                msg = "账号或密码错误" ;
                break;
            case UserService.LOGIN_MSG_FAIL_NON:
                msg = "账号不存在" ;
                break;
            default :
                msg = "登录失败" ;
                break;
        }
        return new ResponseData(
                status ,
                msg ,
                map.get(UserService.KEY_USER)
        ) ;
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @PostMapping("/register")
    public ResponseData register(@RequestBody Map<String , Object> map) {
        User user = new User() ;
        user.setPassword((String)map.get("password"));
        user.setPhone((String)map.get("phone"));
        Integer result = userService.register(user) ;
        String msg = "" ;
        switch (result) {
            case UserService.REG_MSG_OK :
                msg = "注册成功" ;
                break;
            case UserService.REG_MSG_FAIL_NAMEEXISTS :
                msg = "账户已存在" ;
                break;
            case UserService.REG_MSG_FAIL_INFO_NON:
                msg = "注册信息不完整" ;
                break;
            default :
                msg = "注册失败" ;
                break;
        }
        return new ResponseData(
                result ,
                msg,
                result == 0
        ) ;
    }

    /**
     * 添加管理员
     * @param map
     * @return
     */
    @PostMapping("/registerAdmin")
    public ResponseData registerAdmin(@RequestBody Map<String , Object> map) {
        User admin = new User() ;
        admin.setPassword((String)map.get("password"));
        admin.setPhone((String)map.get("phone"));
        admin.setRole(0);
        Integer result = userService.registerAdmin(admin) ;
        String msg = "" ;
        switch (result) {
            case UserService.REG_MSG_OK :
                msg = "注册成功" ;
                break;
            case UserService.REG_MSG_FAIL_NAMEEXISTS :
                msg = "账户已存在" ;
                break;
            case UserService.REG_MSG_FAIL_INFO_NON:
                msg = "注册信息不完整" ;
                break;
            default :
                msg = "注册失败" ;
                break;
        }
        return new ResponseData(
                result ,
                msg,
                result == 0
        ) ;
    }

    /**
     * 根据电话号查询用户信息
     * @param phone
     * @return
     */
    @GetMapping("/searchByPhone/{phone}")
    public ResponseData searchByPhone(@PathVariable("phone")String phone){
        User result=userService.searchByPhone(phone);
        return new ResponseData(
                result !=null ? 0 : 1 ,
                result !=null ? "查找成功" : "查找失败" ,
                result
        );
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @PostMapping("/modifyPwd")
    public ResponseData modifyPwd(@RequestBody Map<String , Object> map){
        Integer result=userService.modifyPwd((String)map.get("password"), (String)map.get("phone"));
        return new ResponseData(
                result !=null ? 0 : 1 ,
                result !=null ? "修改成功" : "修改失败" ,
                result
        );
    }

    /**
     * 查询所有管理员
     * @return
     */
    @GetMapping("/getAllAdmin")
    public ResponseData getAllAdmin(){
        List<User> adminList = userService.getAllAdmin();
        return new ResponseData(
                adminList != null?0:1,
                adminList != null?"查询成功":"查询失败",
                adminList
        );
    }

    /**
     * 查询所有用户（除管理员）
     * @return
     */
    @GetMapping("/getAllUser")
    public ResponseData getAllUser(){
        List<User> userList = userService.getAllUser();
        return new ResponseData(
                userList != null?0:1,
                userList != null?"查询成功":"查询失败",
                userList
        );
    }

    /**
     * 查询所有自愿者
     * @return
     */
    @GetMapping("/getAllVolunteer")
    public ResponseData getAllVolunteer(){
        List<Volunteer> volunteerList = userService.getAllVolunteer();
        return new ResponseData(
                volunteerList != null?0:1,
                volunteerList != null?"查询成功":"查询失败",
                volunteerList
        );
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @PostMapping("/update")
    public ResponseData update(@RequestBody Map<String , Object> map) throws ParseException {
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("user")));
        User user = new User();
        user.setId(Integer.valueOf(json.getString("id")));
        user.setName(json.getString("name"));
//        user.setPassword(json.getString("password"));
        if (json.getString("birthday")!=null){
            user.setBirthday(new SimpleDateFormat("yyyy年MM月dd日").parse(json.getString("birthday")));
        }
        user.setSex(json.getString("sex"));
        user.setEmail(json.getString("email"));
        user.setPhotourl(json.getString("photourl"));
        user.setPhone(json.getString("phone"));
        user.setRole(Integer.valueOf(json.getString("role")));
        User u = userService.update(user) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "更新成功" : "更新失败" ,
                u
        ) ;
    }

    /**
     * 根据id获取数据库相关信息
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public ResponseData getUserById(@RequestParam(value = "id",required = false) Integer id) {
        User u = userService.getUserById(id) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "获取成功" : "获取失败" ,
                u
        );
    }

    /**
     * 根据关键字查询用户
     * @param keyword
     * @return
     */
    @GetMapping("/getUser/{keyword}")
    public ResponseData getUser(@PathVariable("keyword") String keyword) {
        List<User> u = userService.getUser(keyword) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "查询成功" : "查询失败" ,
                u
        );
    }

    /**
     * 查询审核通过的自愿者
     * @return
     */
    @GetMapping("/getV")
    public ResponseData getV() {
        List<Volunteer> v = userService.getV() ;
        return new ResponseData(
                v !=null ? 0 : 1 ,
                v !=null ? "筛选成功" : "筛选失败" ,
                v
        );
    }

    /**
     * 查询审核中的自愿者
     * @return
     */
    @GetMapping("/getNotV")
    public ResponseData getNotV() {
        List<Volunteer> v = userService.getNotV() ;
        return new ResponseData(
                v !=null ? 0 : 1 ,
                v !=null ? "筛选成功" : "筛选失败" ,
                v
        );
    }

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    @GetMapping("/getVolunteer/{keyword}")
    public ResponseData getVolunteer(@PathVariable("keyword") String keyword) {
        List<Volunteer> v = userService.getVolunteer(keyword) ;
        return new ResponseData(
                v !=null ? 0 : 1 ,
                v !=null ? "查询成功" : "查询失败" ,
                v
        );
    }

    /**
     * 根据关键字查询管理员
     * @param keyword
     * @return
     */
    @GetMapping("/getAdmin/{keyword}")
    public ResponseData getAdmin(@PathVariable("keyword") String keyword) {
        List<User> u = userService.getAdmin(keyword) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "查询成功" : "查询失败" ,
                u
        );
    }

    /**
     * 根据id删除相关用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseData deleteById(@PathVariable("id") Integer id) {
        int result = userService.deleteById(id) ;
        return  new ResponseData(
                result !=0 ? 0 : 1 ,
                result !=0 ? "删除成功" : "删除失败" ,
                result
        ) ;
    }

    /***
     * 根据vid获取
     * @param vid
     * @return
     */
    @GetMapping("/getConsultantsById/{vid}")
    public ResponseData getConsultantsById(@PathVariable("vid") Integer vid) {
        List<Consultants> consultants = userService.getConsultantsById(vid) ;
        return new ResponseData(
                consultants !=null ? 0 : 1 ,
                consultants !=null ? "查询成功" : "查询失败" ,
                consultants
        );
    }

    /**
     * 获取用户数据
     * @return
     */
    @GetMapping("/getUserCountData")
    public ResponseData getUserCountData(){
        JSONArray jsonArray = new JSONArray();
        List<String> listRole = new ArrayList<String>();
        listRole.add("普通用户");
        listRole.add("自愿者");
        listRole.add("管理员");
        Integer num = getNum("all");
        for(int i=0;i<listRole.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("角色",listRole.get(i));
            jsonObject.put("总数量", num);
            jsonObject.put("占比", getNum(listRole.get(i)));
            jsonArray.add(i, jsonObject);
        }
        return new ResponseData(
                0,
                "获取成功",
                jsonArray
        );
    }

    public Integer getNum(String role){
        if(role.equals("普通用户")){
            return userService.getUserNum();
        }
        if(role.equals("自愿者")){
            return userService.getVolunteerNum();
        }
        if(role.equals("管理员")){
            return userService.getAdminNum();
        }
        return userService.getAdminNum()+userService.getUserNum()+userService.getVolunteerNum();
    }

    /**
     * 获取自愿者申请数据
     * @return
     */
    @GetMapping("/getVolunteerData")
    public ResponseData getVolunteerData() throws ParseException {
        JSONArray jsonArray = new JSONArray();
        String lastTime = userService.getLastVolunteerData() ;
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(lastTime);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
        for(int i=0;i<7;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("申请时间", time);
            List<Volunteer> volunteers = userService.getVolunteerByTime(time);
            jsonObject.put("申请人数", volunteers.size());
            List<Volunteer> volunteersPass = userService.getVolunteerPassByTime(time);
            jsonObject.put("审核通过人数", volunteersPass.size());
            jsonArray.add(i, jsonObject);
            if (i!=6){
                Calendar c = Calendar.getInstance();
                c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time));
                c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
                time = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
            }
        }
        return new ResponseData(
                0,
                "获取成功",
                jsonArray
        );
    }

    /**
     * 获取树洞发布数据
     * @return
     */
    @GetMapping("/getSayData")
    public ResponseData getSayData() throws ParseException {
        JSONArray jsonArray = new JSONArray();
        String lastTime = userService.getLastSayData() ;
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(lastTime);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
        for(int i=0;i<7;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("发布时间", time);
            List<Say> say = userService.getSayByTime(time);
            jsonObject.put("发布数量", say.size());
            jsonArray.add(i, jsonObject);
            if (i!=6){
                Calendar c = Calendar.getInstance();
                c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time));
                c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
                time = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
            }
        }
        return new ResponseData(
                0,
                "获取成功",
                jsonArray
        );
    }

    /**
     * 根据name获取自愿者相关信息
     * @param name
     * @return
     */
    @GetMapping("/getVolunteerByName")
    public ResponseData getVolunteerByName(@RequestParam(value = "name",required = false) String name) {
        Volunteer volunteer = userService.getVolunteerByName(name) ;
        return new ResponseData(
                volunteer !=null ? 0 : 1 ,
                volunteer !=null ? "获取成功" : "获取失败" ,
                volunteer
        );
    }

    /**
     * 更新自愿者
     * @param map
     * @return
     */
    @PostMapping("/updateVByName")
    public ResponseData updateVByName(@RequestBody Map<String , Object> map) throws ParseException {
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("volunteer")));
        Volunteer volunteer = new Volunteer();
        volunteer.setName(json.getString("name"));
        volunteer.setRealName(json.getString("realName"));
        volunteer.setGender(json.getString("gender"));
//        if (json.getString("birth")!=null){
//            volunteer.setBirth(new SimpleDateFormat("yyyy年MM月dd日").parse(json.getString("birth")));
//        }
        volunteer.setComeFrom(json.getString("comeFrom"));
        volunteer.setPhoneNum(json.getString("phoneNum"));
        volunteer.setIdentity(json.getString("identity"));
        volunteer.setCertificate(json.getString("certificate"));
        volunteer.setDiploma(json.getString("diploma"));
        Volunteer u = userService.updateVByName(volunteer) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "更新成功" : "更新失败" ,
                u
        ) ;
    }

    /**
     * 根据id查询身份
     * @param id
     * @return
     */
    @GetMapping("/getRoleById")
    public ResponseData getRoleById(@RequestParam(value = "id",required = false) Integer id) {
        String role = userService.getRoleById(id) ;
        return new ResponseData(
                role !=null ? 0 : 1 ,
                role !=null ? "获取成功" : "获取失败" ,
                role
        );
    }

    /**
     * 修改自愿者状态
     * @param map
     * @return
     */
    @PostMapping("/updateVSateByName")
    public ResponseData updateVSateByName(@RequestBody Map<String , Object> map) throws ParseException {
//        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("volunteer")));
        Volunteer volunteer = new Volunteer();
        volunteer.setName((String) map.get("name"));
        volunteer.setState((String) map.get("state"));
        System.out.println(map.get("state"));
        if(map.get("state").equals("通过")){
            volunteer.setRole(2);
        }else {
            volunteer.setRole(1);
        }
        Volunteer u = userService.updateVSateByName(volunteer) ;
        return new ResponseData(
                u !=null ? 0 : 1 ,
                u !=null ? "更新成功" : "更新失败" ,
                u
        ) ;
    }

    /**
     * 根据name从用户表中获取sender(sex)/birth(birthday)/phoneNum(phone)
     * @param name
     * @return
     */
    @GetMapping("/getUserByName")
    public ResponseData getUserByName(@RequestParam(value = "name",required = false) String name) {
        Volunteer volunteer = userService.getUserByName(name) ;
        return new ResponseData(
                volunteer !=null ? 0 : 1 ,
                volunteer !=null ? "获取成功" : "获取失败" ,
                volunteer
        );
    }

    /**
     * 提交自愿者申请
     * @param map
     * @return
     */
    @PostMapping("/addVRequest")
    public ResponseData addVRequest(@RequestBody Map<String , Object> map) throws ParseException {
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map.get("volunteer")));
        Volunteer volunteer = new Volunteer();
        volunteer.setName(jsonObject.getString("name"));
        volunteer.setRealName(jsonObject.getString("realName"));
        volunteer.setGender(jsonObject.getString("gender"));
        if (jsonObject.getString("birth")!=null){
            volunteer.setBirth(new SimpleDateFormat("yyyy年MM月dd日").parse(jsonObject.getString("birth")));
        }
        volunteer.setComeFrom(jsonObject.getString("comeFrom"));
        volunteer.setPhoneNum(jsonObject.getString("phoneNum"));
        volunteer.setIdentity(jsonObject.getString("identity"));
        volunteer.setCertificate(jsonObject.getString("certificate"));
        volunteer.setDiploma(jsonObject.getString("diploma"));
        Integer result = userService.addVRequest(volunteer) ;
        String msg = "" ;
        switch (result) {
            case UserService.REG_MSG_OK :
                msg = "提交申请成功" ;
                break;
            case UserService.REG_MSG_FAIL_NAMEEXISTS :
                msg = "已提交申请，目前处于审核中，请勿重复提交" ;
                break;
            case UserService.REG_MSG_FAIL_INFO_NON:
                msg = "信息不完整" ;
                break;
            default :
                msg = "提交申请失败" ;
                break;
        }
        return new ResponseData(
                result ,
                msg,
                result == 0
        ) ;
    }

    /**
     * （树洞）发送say
     * @param map
     * @return
     */
    @PostMapping("/sendSay")
    public ResponseData sendSay(@RequestBody Map<String , Object> map) {
        Say say = new Say();
        say.setSender((String) map.get("sender"));
        say.setContent((String) map.get("content"));
        Integer result = userService.sendSay(say) ;
        String msg = "" ;
        switch (result) {
            case UserService.REG_MSG_OK :
                msg = "发送成功" ;
                break;
            case UserService.REG_MSG_FAIL_INFO_NON:
                msg = "信息不完整" ;
                break;
            default :
                msg = "发送失败" ;
                break;
        }
        return new ResponseData(
                result ,
                msg,
                result == 0
        ) ;
    }

    /**
     * （树洞）随机获取say
     * @return
     */
    @GetMapping("/getRandSay")
    public ResponseData getRandSay(){
        Say say = userService.getRandSay();
        return new ResponseData(
                say != null?0:1,
                say != null?"获取成功":"获取失败",
                say
        );
    }

    /**
     * 根据用户id获取消息
     * @param receiver
     * @return
     */
    @GetMapping("/getMsgList")
    public ResponseData getMsgList(@RequestParam(value = "receiver",required = false) String receiver) {
        List<Comment> comments = userService.getMsgList(receiver) ;
        return new ResponseData(
                comments !=null ? 0 : 1 ,
                comments !=null ? "获取成功" : "获取失败" ,
                comments
        );
    }

    /**
     * 获取咨询者
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public ResponseData getById(@RequestParam(value = "id",required = false) Integer id) {
        Consultants consultants = userService.getById(id) ;
        return new ResponseData(
                consultants !=null ? 0 : 1 ,
                consultants !=null ? "获取成功" : "获取失败" ,
                consultants
        );
    }

    /**
     * 根据id修改咨询者状态
     * @param map
     * @return
     */
    @PostMapping("/updateStateById")
    public ResponseData updateStateById(@RequestBody Map<String , Object> map){
        Integer result = userService.updateStateById((String) map.get("state"),(Integer) map.get("id"));
        return new ResponseData(
                result !=0 ? 0 : 1 ,
                result !=0 ? "修改成功" : "修改失败" ,
                result
        );
    }

}
