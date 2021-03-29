package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.entity.Article;
import com.example.liu.weidea.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

//import com.isoft.video.util.FileUtil;

@CrossOrigin
@RestController
@RequestMapping("/Article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

//    /**
//     * 登录
//     * @param phone
//     * @param password
//     * @return
//     */
//    @GetMapping("/login")
//    public ResponseData login(@RequestParam(value="phone" ,required =false) String phone,
//                              @RequestParam(value="password" ,required =false) String password) {
//        // 调用service中方法进行login处理
//        Map<String , Object> map = articleService.loginCheck(phone , password) ;
//        String msg = "" ;
//        Integer status = (Integer)(map.get(articleService.KEY_MSG)) ;
//        switch (status) {
//            case articleService.LOGIN_MSG_OK :
//                msg = "登录成功" ;
//                break;
//            case articleService.LOGIN_MSG_FAIL_ERROR :
//                msg = "账号或密码错误" ;
//                break;
//            case articleService.LOGIN_MSG_FAIL_NON:
//                msg = "账号不存在" ;
//                break;
//            default :
//                msg = "登录失败" ;
//                break;
//        }
//        return new ResponseData(
//                status ,
//                msg ,
//                map.get(articleService.KEY_Article)
//        ) ;
//    }
//
//    /**
//     * 注册
//     * @param map
//     * @return
//     */
//    @PostMapping("/register")
//    public ResponseData register(@RequestBody Map<String , Object> map) {
//        Article Article = new Article() ;
//        Article.setPassword((String)map.get("password"));
//        Article.setPhone((String)map.get("phone"));
//        Integer result = articleService.register(Article) ;
//        String msg = "" ;
//        switch (result) {
//            case articleService.REG_MSG_OK :
//                msg = "注册成功" ;
//                break;
//            case articleService.REG_MSG_FAIL_NAMEEXISTS :
//                msg = "账户已存在" ;
//                break;
//            case articleService.REG_MSG_FAIL_INFO_NON:
//                msg = "注册信息不完整" ;
//                break;
//            default :
//                msg = "注册失败" ;
//                break;
//        }
//        return new ResponseData(
//                result ,
//                msg,
//                result == 0
//        ) ;
//    }
//
//    /**
//     * 添加管理员
//     * @param map
//     * @return
//     */
//    @PostMapping("/registerAdmin")
//    public ResponseData registerAdmin(@RequestBody Map<String , Object> map) {
//        Article admin = new Article() ;
//        admin.setPassword((String)map.get("password"));
//        admin.setPhone((String)map.get("phone"));
//        admin.setRole(0);
//        Integer result = articleService.registerAdmin(admin) ;
//        String msg = "" ;
//        switch (result) {
//            case articleService.REG_MSG_OK :
//                msg = "注册成功" ;
//                break;
//            case articleService.REG_MSG_FAIL_NAMEEXISTS :
//                msg = "账户已存在" ;
//                break;
//            case articleService.REG_MSG_FAIL_INFO_NON:
//                msg = "注册信息不完整" ;
//                break;
//            default :
//                msg = "注册失败" ;
//                break;
//        }
//        return new ResponseData(
//                result ,
//                msg,
//                result == 0
//        ) ;
//    }
//
//    /**
//     * 根据电话号查询用户信息
//     * @param phone
//     * @return
//     */
//    @GetMapping("/searchByPhone/{phone}")
//    public ResponseData searchByPhone(@PathVariable("phone")String phone){
//        Article result= articleService.searchByPhone(phone);
//        return new ResponseData(
//                result !=null ? 0 : 1 ,
//                result !=null ? "查找成功" : "查找失败" ,
//                result
//        );
//    }
//
//    /**
//     * 修改密码
//     * @param map
//     * @return
//     */
//    @PostMapping("/modifyPwd")
//    public ResponseData modifyPwd(@RequestBody Map<String , Object> map){
//        Integer result= articleService.modifyPwd((String)map.get("password"), (String)map.get("phone"));
//        return new ResponseData(
//                result !=null ? 0 : 1 ,
//                result !=null ? "修改成功" : "修改失败" ,
//                result
//        );
//    }
//
//    /**
//     * 查询所有管理员
//     * @return
//     */
//    @GetMapping("/getAllAdmin")
//    public ResponseData getAllAdmin(){
//        List<Article> adminList = articleService.getAllAdmin();
//        return new ResponseData(
//                adminList != null?0:1,
//                adminList != null?"查询成功":"查询失败",
//                adminList
//        );
//    }
//
//    /**
//     * 查询所有用户（除管理员）
//     * @return
//     */
//    @GetMapping("/getAllArticle")
//    public ResponseData getAllArticle(){
//        List<Article> ArticleList = articleService.getAllArticle();
//        return new ResponseData(
//                ArticleList != null?0:1,
//                ArticleList != null?"查询成功":"查询失败",
//                ArticleList
//        );
//    }
//
//    /**
//     * 查询所有自愿者
//     * @return
//     */
//    @GetMapping("/getAllVolunteer")
//    public ResponseData getAllVolunteer(){
//        List<Article> volunteerList = articleService.getAllVolunteer();
//        return new ResponseData(
//                volunteerList != null?0:1,
//                volunteerList != null?"查询成功":"查询失败",
//                volunteerList
//        );
//    }
//
////    public List<Article> getAll() {
////        return ArticleService.getAll() ;
////    }
//
//    /**
//     * 更新
//     * @param map
//     * @return
//     */
//    @PostMapping("/update")
//    public ResponseData update(@RequestBody Map<String , Object> map) throws ParseException {
//        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map.get("Article")));
//        Article Article = new Article();
//        Article.setId(Integer.valueOf(json.getString("id")));
//        Article.setName(json.getString("name"));
//        Article.setPassword(json.getString("password"));
//        if (json.getString("birthday")!=null){
//            Article.setBirthday(new SimpleDateFormat("yyyy年MM月dd日").parse(json.getString("birthday")));
//        }
//        Article.setSex(json.getString("sex"));
//        Article.setEmail(json.getString("email"));
//        Article.setPhotourl(json.getString("photourl"));
//        Article.setPhone(json.getString("phone"));
//        Article.setRole(Integer.valueOf(json.getString("role")));
//        Article u = articleService.update(Article) ;
//        return new ResponseData(
//                u !=null ? 0 : 1 ,
//                u !=null ? "更新成功" : "更新失败" ,
//                u
//        ) ;
//    }
//
//    /**
//     * 根据id获取数据库相关信息
//     * @param id
//     * @return
//     */
//    @GetMapping("/getArticleById")
//    public ResponseData getArticleById(@RequestParam(value = "id",required = false) Integer id) {
//        Article u = articleService.getArticleById(id) ;
//        return new ResponseData(
//                u !=null ? 0 : 1 ,
//                u !=null ? "获取成功" : "获取失败" ,
//                u
//        );
//    }
//
//    /**
//     * 根据关键字查询用户
//     * @param keyword
//     * @return
//     */
//    @GetMapping("/getArticle/{keyword}")
//    public ResponseData getArticle(@PathVariable("keyword") String keyword) {
//        List<Article> u = articleService.getArticle(keyword) ;
//        return new ResponseData(
//                u !=null ? 0 : 1 ,
//                u !=null ? "查询成功" : "查询失败" ,
//                u
//        );
//    }
//
//    /**
//     * 根据关键字查询自愿者
//     * @param keyword
//     * @return
//     */
//    @GetMapping("/getVolunteer/{keyword}")
//    public ResponseData getVolunteer(@PathVariable("keyword") String keyword) {
//        List<Article> u = articleService.getVolunteer(keyword) ;
//        return new ResponseData(
//                u !=null ? 0 : 1 ,
//                u !=null ? "查询成功" : "查询失败" ,
//                u
//        );
//    }
//
//    /**
//     * 根据关键字查询管理员
//     * @param keyword
//     * @return
//     */
//    @GetMapping("/getAdmin/{keyword}")
//    public ResponseData getAdmin(@PathVariable("keyword") String keyword) {
//        List<Article> u = articleService.getAdmin(keyword) ;
//        return new ResponseData(
//                u !=null ? 0 : 1 ,
//                u !=null ? "查询成功" : "查询失败" ,
//                u
//        );
//    }
//
//    /**
//     * 根据id删除相关用户信息
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseData deleteById(@PathVariable("id") Integer id) {
//        int result = articleService.deleteById(id) ;
//        return  new ResponseData(
//                result !=0 ? 0 : 1 ,
//                result !=0 ? "删除成功" : "删除失败" ,
//                result
//        ) ;
//    }
//
//
//    @GetMapping("RolePage/{curPage}/{size}")
//    public Map<String , Object> page(@PathVariable("curPage") Integer curPage , @PathVariable("size")Integer size ){
//        Page<Article> page = ArticleService.RolePage(curPage , size) ;
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
//        Article Article = new Article() ;
//        Article.setName((String)map.get("name"));
//        Article.setPassword((String)map.get("password"));
////        Article.setAge(Integer.parseInt(map.get("age").toString()));
//        Article.setSex((String)map.get("sex"));
//        Article.setEmail((String)map.get("email"));
//        Article.setPhone((String)map.get("mobile"));
////        Article.setPhotourl((String)map.get("photourl"));
//        Integer result = ArticleService.addRole(Article) ;
//        String msg = "" ;
//        switch (result) {
//            case ArticleService.REG_MSG_OK :
//                msg = "添加成功" ;
//                break;
//            case ArticleService.REG_MSG_FAIL_NAMEEXISTS :
//                msg = "账户已存在，可从用户列表修改为管理员权限" ;
//                break;
//            case ArticleService.REG_MSG_FAIL_INFO_NON:
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
//        Integer r=ArticleService.upRole(id);
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
//        Page<Article> page = ArticleService.newsPage(id , name , regdate , curPage , size) ;
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
//        String photoUrl=ArticleService.getPhotoUrl(name);
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
//    @Value("${Articlephoto.path}")    // 从配置文件中读取 Articlephoto.path属性的值
//    private String upPhotoPath ;
//    @PostMapping("/photo")
//    public ResponseData upPhoto(@RequestParam("Articlephoto") MultipartFile file, Integer id , HttpServletRequest request) {
//        // 保存是否成功boolean
//        boolean  result = false ;
//        // 上传文件物理路径,构造文件名
//        String photoPath , newFile , photoUri = null;
//
//        Article ArticleResult = null ;
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
//                Article Article = new Article();
//                Article.setId(id);
////                Article.setAge(Article.getAge());
////                Article.setSex(Article.getSex());
////                Article.setEmail(Article.getEmail());
////                Article.setMobile(Article.getMobile());
////                Article.setAddress(Article.getAddress());
//                Article.setPhotourl(newFile);
//                ArticleResult = ArticleService.update(Article) ;
////                System.out.println("ArticleResult-----"+ArticleResult);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return new ResponseData(
//                ArticleResult != null ? 0 : 1 ,
//                ArticleResult != null  ? "修改成功" : "修改失败" ,
//                ArticleResult
//        ) ;
//    }
//
//
//
//    //获取数据库全部信息
//    @GetMapping("/getAll")
//    public List<Article> getAll() {
//        return ArticleService.getAll() ;
//    }
//
//    @DeleteMapping("delRole/{id}")
//    public ResponseData delRoleById(@PathVariable("id")Integer id){
//        Integer r =ArticleService.delById(id);
//        return new ResponseData(
//                r != null ? 0 : 1 ,
//                r != null ? "删除" : "删除失败" ,
//                r
//        );
//    }
}
