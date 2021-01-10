package com.example.liu.weidea.controller;

import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.User;
import com.example.liu.weidea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

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
     * @param name
     * @param password
     * @return
     */
    @GetMapping("login/{name}/{pwd}")
    public ResponseData login(@PathVariable("name") String name , @PathVariable("pwd") String password) {
        // 调用service中方法进行login处理
        Map<String , Object> map = userService.loginCheck(name , password) ;
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
    @PostMapping("register")
    public ResponseData register(@RequestBody Map<String , Object> map) {
        User user = new User() ;
        user.setName((String)map.get("name"));
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

    @GetMapping("/searchByName/{name}")
    public ResponseData searchByname(@PathVariable("name")String name){
        User result=userService.searchByName(name);
        return new ResponseData(
                result !=null ? 0 : 1 ,
                result !=null ? "查找成功" : "查找失败" ,
                result
        );
    }

//    @PutMapping("/forget/{password}/{name}")
//    public ResponseData forget(@PathVariable("password")String password, @PathVariable("name")String name){
//        Integer result=userService.forget(password, name);
//        return new ResponseData(
//                result !=null ? 0 : 1 ,
//                result !=null ? "修改成功" : "修改失败" ,
//                result
//        );
//    }
//
//    /**
//     * 更新
//     * @param user
//     * @return
//     */
//    @PutMapping("/update")
//    public ResponseData update(User user) {
//        User u = userService.update(user) ;
//        return new ResponseData(
//                u !=null ? 0 : 1 ,
//                u !=null ? "更新成功" : "更新失败" ,
//                u
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
//    /**
//     * 根据id获取数据库相关信息
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public User getById(@PathVariable("id") Integer id) {
//        return userService.getById(id) ;
//    }
//
//    /**
//     * 根据id删除相关用户信息
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseData deleteById(@PathVariable("id") Integer id) {
//        int result = userService.DeleteById(id) ;
//        return  new ResponseData(
//                result !=0 ? 0 : 1 ,
//                result !=0 ? "删除成功" : "删除失败" ,
//                result
//        ) ;
//    }
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
