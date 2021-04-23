package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.*;
import com.example.liu.weidea.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        user.setPassword(json.getString("password"));
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
    public ResponseData getVolunteerData(){
        JSONArray jsonArray = new JSONArray();
        List<String> listRole = new ArrayList<String>();
        List<Volunteer> volunteers = userService.getVolunteerData() ;
        //根据时间排序（年月日）取前七个（少于7个少几个为空）
        for (int i=0;i<7;i++){
            volunteers.get(i);
            String time = String.valueOf(new SimpleDateFormat("2021-04-24"));
            listRole.add(time);
        }

        for(int i=0;i<listRole.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("申请时间",listRole.get(i));
            jsonObject.put("申请人数", getTimeNum(listRole.get(i)));
            jsonArray.add(i, jsonObject);
        }
        return new ResponseData(
                0,
                "获取成功",
                jsonArray
        );
    }

    public Integer getTimeNum(String time){
        return userService.getTimeNum(time);
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

//    /**
//     * 审核自愿者
//     * @param name
//     * @return
//     */
//    @PostMapping("/updateVState/{name}")
//    public ResponseData updateVState(@PathVariable("name") String name) {
//        int result = userService.updateVState(name) ;
//        return  new ResponseData(
//                result !=0 ? 0 : 1 ,
//                result !=0 ? "审核通过" : "审核失败" ,
//                result
//        ) ;
//    }


//
//    @GetMapping("RolePage/{curPage}/{size}")
//    public Map<String , Object> page(@PathVariable("curPage") Integer curPage , @PathVariable("size")Integer size ){
//        Page<User> page = userService.RolePage(curPage , size) ;
//        Map<String  , Object> map = new HashMap<>() ;
//        map.put("total" , page.getRowCount()) ;
//        map.put("rows" , page.getData()) ;
//        return map ;
//    }
//
//    /**
//     * 添加管理员
//     * @param map
//     * @return
//     */
//    @PostMapping("addRole/")
//    public ResponseData addRole(@RequestBody Map<String , Object> map) {
//        User user = new User() ;
//        user.setName((String)map.get("name"));
//        user.setPassword((String)map.get("password"));
////        user.setAge(Integer.parseInt(map.get("age").toString()));
//        user.setSex((String)map.get("sex"));
//        user.setEmail((String)map.get("email"));
//        user.setPhone((String)map.get("mobile"));
////        user.setPhotourl((String)map.get("photourl"));
//        Integer result = userService.addRole(user) ;
//        String msg = "" ;
//        switch (result) {
//            case UserService.REG_MSG_OK :
//                msg = "添加成功" ;
//                break;
//            case UserService.REG_MSG_FAIL_NAMEEXISTS :
//                msg = "账户已存在，可从用户列表修改为管理员权限" ;
//                break;
//            case UserService.REG_MSG_FAIL_INFO_NON:
//                msg = "信息不完整" ;
//                break;
//            default :
//                msg = "添加失败" ;
//                break;
//        }
//        return new ResponseData(
//                result ,
//                msg,
//                result == 0
//        ) ;
//    }
//
//    @PutMapping("role/{id}")
//    public ResponseData upRole(@PathVariable("id")Integer id){
//        Integer r=userService.upRole(id);
//        return new ResponseData(
//                r != null ? 0 : 1 ,
//                r != null ? "修改成功" : "修改失败" ,
//                r
//        );
//    }
//
//    /**
//     * 用户列表的分页实现
//     * @param curPage
//     * @param size
//     * @param id
//     * @param name
//     * @param regdate
//     * @return
//     */
//    @GetMapping("/page/{size}/{curPage}")
//    public Map<String , Object> page(@PathVariable("curPage") Integer curPage , @PathVariable("size") Integer size ,
//                                     Integer id , String name , @DateTimeFormat(pattern = "yyyy年MM月dd日") Date regdate){
//        Page<User> page = userService.newsPage(id , name , regdate , curPage , size) ;
//        Map<String  , Object> map = new HashMap<>() ;
//        map.put("pageCount" , page.getPageCount()) ;   //总页数
//        map.put("curPage",page.getCurPage()) ;  //当前页数
//        map.put("total" , page.getRowCount()) ; //总行数
//        map.put("rows" , page.getData()) ;  //本页数据
//        return map ;
//    }
//
//
//
//    private static String getFileType(String path){
//        String fileType = "";
//        try{
//            InputStream file = new BufferedInputStream(new FileInputStream(path));
//            fileType = URLConnection.guessContentTypeFromStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fileType;
//    }
//
//    //获取头像地址
//    @GetMapping("/photoUrl/{name}")
//    public String getVideos(HttpServletResponse response, @PathVariable("name") String name) throws Exception{
//        String photoUrl=userService.getPhotoUrl(name);
//        //获取resources文件夹的绝对地址
//        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("static/img/"+photoUrl).getPath();
////        System.out.println(sourcePath);
//        FileInputStream fis = null;
//        OutputStream os = null ;
//        fis = new FileInputStream(sourcePath);
//        // 得到文件大小
//        int size = fis.available();
//        byte data[] = new byte[size];
//        // 读数据
//        fis.read(data);
//        fis.close();
////        fis = null;
//        // 设置返回的文件类型
//        String fileType=getFileType(sourcePath);
////        System.out.println("................."+fileType);
//        response.setContentType(fileType);
//        os = response.getOutputStream();
//        os.write(data);
//        os.flush();
//        os.close();
////        os = null;
//        return null;
//    }
//
//    /**
//     * 用户上传头像
//     */
//    @Value("${userphoto.path}")    // 从配置文件中读取 userphoto.path属性的值
//    private String upPhotoPath ;
//    @PostMapping("/photo")
//    public ResponseData upPhoto(@RequestParam("userphoto") MultipartFile file, Integer id , HttpServletRequest request) {
//        // 保存是否成功boolean
//        boolean  result = false ;
//        // 上传文件物理路径,构造文件名
//        String photoPath , newFile , photoUri = null;
//
//        User userResult = null ;
//        if(! file.isEmpty()) {
//            // 构造上传文件新名字
//            // 1）获取上传文件名
//            String originName = file.getOriginalFilename() ;
//            // 2）获取文件扩展名
//            String extName = originName.substring(originName.lastIndexOf(".")) ;
//            // 3）构造文件新名字
//            newFile = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + "_" + id + extName ;
//            System.out.println("-------------------"+newFile);
//
////            // 构造文件上传保存物理路径
////            ServletContext app = request.getServletContext() ;
////            photoPath = app.getRealPath(upPhotoPath) + "/" ;
//
//            String sourcePath = ClassUtils.getDefaultClassLoader().getResource("static/img/").getPath();
//            System.out.println("sourcePath:"+sourcePath);
//
//            // 判断文件保存的物理路径是否存在，不存在创建
//            File f = new File(sourcePath) ;
//            if(! f.exists()) {
//                f.mkdirs() ;
//            }
//            // 保存文件
//            try {
//                photoPath=sourcePath + newFile;
//                file.transferTo(new File(photoPath));
////                System.out.println(new File(photoPath  + "---ok"));
////                result  = true ;
////                // 构造所保存文件的基于http协议的uri
////                photoUri = FileUtil.url(request , upPhotoPath , newFile) ;
////                System.out.println(photoUri);
////                photoUri =
//                // 修改数据库
//                User user = new User();
//                user.setId(id);
////                user.setAge(user.getAge());
////                user.setSex(user.getSex());
////                user.setEmail(user.getEmail());
////                user.setMobile(user.getMobile());
////                user.setAddress(user.getAddress());
//                user.setPhotourl(newFile);
//                userResult = userService.update(user) ;
////                System.out.println("userResult-----"+userResult);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return new ResponseData(
//                userResult != null ? 0 : 1 ,
//                userResult != null  ? "修改成功" : "修改失败" ,
//                userResult
//        ) ;
//    }
//
//
//
//    //获取数据库全部信息
//    @GetMapping("/getAll")
//    public List<User> getAll() {
//        return userService.getAll() ;
//    }
//
//    @DeleteMapping("delRole/{id}")
//    public ResponseData delRoleById(@PathVariable("id")Integer id){
//        Integer r =userService.delById(id);
//        return new ResponseData(
//                r != null ? 0 : 1 ,
//                r != null ? "删除" : "删除失败" ,
//                r
//        );
//    }
}
