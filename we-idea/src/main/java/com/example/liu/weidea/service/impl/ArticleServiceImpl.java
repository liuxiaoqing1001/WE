package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.ArticleDao;
import com.example.liu.weidea.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

//    /**
//     * 登录
//     * @param phone  非空
//     * @param pwd  非空
//     * @return
//     */
//    @Override
//    public Map<String, Object> loginCheck(String phone, String pwd) {
//        Map<String , Object> map = new HashMap<>() ;
//        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)) {
//            map.put(KEY_MSG , LOGIN_MSG_FAIL_OTHER) ;
//            return map ;
//        }
//        // 先进行账号是否存在检测
//        int nameCount = articleDao.ArticleSearch(phone) ;
//        if(nameCount == 0) {
//            map.put(KEY_MSG , LOGIN_MSG_FAIL_NON) ;
//            return map ;
//        }
//        // 账号存在，登录检查
//        Article u = articleDao.select(phone , DigestUtils.md5DigestAsHex(pwd.getBytes())) ;
//        if(u == null) {
//            map.put(KEY_MSG , LOGIN_MSG_FAIL_ERROR) ;
//            return map ;
//        }
//        map.put(KEY_Article , u) ;
//        map.put(KEY_MSG , LOGIN_MSG_OK) ;
//        if (u.getRole() == 0) {
//            map.put(KEY_ROLE , ROLE_ADMIN) ;
//        } else if (u.getRole() == 1) {
//            map.put(KEY_ROLE , ROLE_Article) ;
//        }
//        return map;
//    }
//
//    /**
//     * 注册
//     * @param Article 非空，电话和密码非空
//     * @return
//     */
//    @Override
//    public Integer register(Article Article) {
//        if(null == Article) {
//            return REG_MSG_FAIL_INFO_NON ;
//        }
//        if(StringUtils.isEmpty(Article.getPassword())||StringUtils.isEmpty(Article.getPhone()) ) {
//            return REG_MSG_FAIL_INFO_NON ;
//        }
//        // 先进行账号是否存在检测
//        int count = articleDao.ArticleSearch(Article.getPhone()) ;
//        if(count > 0) {
//            return REG_MSG_FAIL_NAMEEXISTS ;
//        }
//        // 注册
//        Article.setPassword(DigestUtils.md5DigestAsHex(Article.getPassword().getBytes()));
//        Article.setPhone(Article.getPhone());
//        int r = articleDao.add(Article) ;
//        if(r > 0) {
//            return REG_MSG_OK ;
//        } else {
//            return REG_MSG_FAIL_OTHER ;
//        }
//    }
//
//    /**
//     * 添加管理员
//     * @param Article 非空，电话和密码非空
//     * @return
//     */
//    @Override
//    public Integer registerAdmin(Article Article) {
//        if( Article == null ) {
//            return REG_MSG_FAIL_INFO_NON ;
//        }
//        if(StringUtils.isEmpty(Article.getPassword())||StringUtils.isEmpty(Article.getPhone()) ) {
//            return REG_MSG_FAIL_INFO_NON ;
//        }
//        // 先进行账号是否存在检测
//        int count = articleDao.ArticleSearch(Article.getPhone()) ;
//        if(count > 0) {
//            return REG_MSG_FAIL_NAMEEXISTS ;
//        }
//        // 注册
//        Article.setPassword(DigestUtils.md5DigestAsHex(Article.getPassword().getBytes()));
//        Article.setPhone(Article.getPhone());
//        Article.setRole(Article.getRole());
//        int r = articleDao.addAdmin(Article) ;
//        if(r > 0) {
//            return REG_MSG_OK ;
//        } else {
//            return REG_MSG_FAIL_OTHER ;
//        }
//    }
//
//    /**
//     * 根据电话号查询用户信息
//     * @param phone
//     * @return
//     */
//    @Override
//    public Article searchByPhone(String phone) {
//        if(phone==""||phone==null){
//            return null;
//        }
//        return articleDao.searchByPhone(phone);
//    }
//
//    /**
//     * 修改秘密
//     * @param password
//     * @param phone
//     * @return
//     */
//    @Override
//    public Integer modifyPwd(String password, String phone) {
//        if(phone==null||phone==""){
//            return null;
//        }
//        password= DigestUtils.md5DigestAsHex(password.getBytes());
//        return articleDao.modifyPwd(password, phone);
//    }
//
//    /**
//     * 查询数据库中所有管理员
//     * @return
//     */
//    @Override
//    public List<Article> getAllAdmin() {
//        return articleDao.getAllAdmin();
//    }
//
//    /**
//     * 查询数据库中所有用户（除管理员）
//     * @return
//     */
//    @Override
//    public List<Article> getAllArticle() {
//        return articleDao.getAllArticle();
//    }
//
//    /**
//     * 查询数据库中所有自愿者
//     * @return
//     */
//    @Override
//    public List<Article> getAllVolunteer() {
//        return articleDao.getAllVolunteer();
//    }
//
//    /**
//     * 修改
//     * @param Article
//     * @return
//     */
//    @Override
//    public Article update(Article Article) {
//        if(null == Article) {
//            return null ;
//        }
//        if(Article.getId() == null || Article.getId() < 1) {
//            return null ;
//        }
//        if(null != Article.getPassword()) {
//            Article.setPassword(DigestUtils.md5DigestAsHex(Article.getPassword().getBytes()));
//        }
//        int r = articleDao.updateById(Article) ;
//        if(r != 1) {
//            return null ;
//        } else {
//            return articleDao.getArticleById(Article.getId()) ;
//        }
//    }
//
//    /**
//     * 根据id获取用户信息
//     * @param id
//     * @return
//     */
//    @Override
//    public Article getArticleById(Integer id) {
//        return articleDao.getArticleById(id);
//    }
//
//    /**
//     * 根据id删除用户信息
//     * @param id
//     * @return
//     */
//    @Override
//    public int deleteById(Integer id) {
//        return articleDao.deleteById(id);
//    }
//
//    /**
//     * 根据关键字查询用户
//     * @param keyword
//     * @return
//     */
//    @Override
//    public List<Article> getArticle(String keyword) {
//        if(keyword==""||keyword==null){
//            return articleDao.getAllArticle();
//        }
//        return articleDao.getArticle(keyword);
//    }
//
//    /**
//     * 根据关键字查询自愿者
//     * @param keyword
//     * @return
//     */
//    @Override
//    public List<Article> getVolunteer(String keyword) {
//        if(keyword==""||keyword==null){
//            return articleDao.getAllVolunteer();
//        }
//        return articleDao.getVolunteer(keyword);
//    }
//
//    /**
//     * 根据关键字查询管理员
//     * @param keyword
//     * @return
//     */
//    @Override
//    public List<Article> getAdmin(String keyword) {
//        if(keyword==""||keyword==null){
//            return articleDao.getAllAdmin();
//        }
//        return articleDao.getAdmin(keyword);
//    }
//
//
//
//
//
//
//
//
//
//    @Override
//    public Page<Article> newsPage(Integer id, String name, Date regdate, Integer curPage, Integer size) {
//        if(null == curPage || curPage < 1) {
//            curPage = 1 ;
//        }
//        if(null == size || size < 1) {
//            size = 10 ;
//        }
//        String pubdateStr = null ;
//        if(null != regdate) {
//            pubdateStr = new SimpleDateFormat("yyyyMMdd").format(regdate) ;
//        }
//        List<Article> list = articleDao.getMoreBy(id , name , pubdateStr , size * (curPage-1) , size) ;
//        int count = articleDao.getMoreCount(id , name , pubdateStr) ;
//        int pageCount = (int) Math.ceil(count * 1.0 / size);
//        Page<Article> pageInfo = new Page() ;
//        pageInfo.setData((ArrayList<Article>) list);
//        pageInfo.setCurPage(curPage);
//        pageInfo.setPageCount(pageCount);
//        pageInfo.setRowCount(count) ;
//        pageInfo.setSize(size);
//        return pageInfo ;
//    }
//
//    @Override
//    public List<Article> getAll() {
//        return articleDao.getAll();
//    }
//
//    @Override
//    public Integer upRole(Integer id) {
//        if(id == null || id<1) {
//            return null ;
//        }
//        int r = articleDao.upRole(id) ;
//        if(r != 1) {
//            return null ;
//        } else {
//            return articleDao.upRole(id) ;
//        }
//    }
//
//    @Override
//    public Integer delById(Integer id) {
//        if(id == null || id<1) {
//            return null ;
//        }
//        int r = articleDao.delById(id) ;
//        if(r != 1) {
//            return null ;
//        } else {
//            return articleDao.delById(id) ;
//        }
//    }
//
//    @Override
//    public Page<Article> RolePage(Integer curPage, Integer size) {
//        if(null == curPage || curPage < 1) {
//            curPage = 1 ;
//        }
//        if(null == size || size < 1) {
//            size = 10 ;
//        }
//        List<Article> list = articleDao.getLimit(size * (curPage-1) , size) ;
//        int count = articleDao.getCount() ;
//        int pageCount = (int) Math.ceil(count * 1.0 / size);
//        Page<Article> pageInfo = new Page() ;
//        pageInfo.setData((ArrayList<Article>) list);
//        pageInfo.setCurPage(curPage);
//        pageInfo.setPageCount(pageCount);
//        pageInfo.setRowCount(count) ;
//        pageInfo.setSize(size);
//        return pageInfo ;
//    }
//
//    @Override
//    public Integer addRole(Article Article) {
////        System.out.println(Article.toString());
//        if(null == Article) {
//            return REG_MSG_FAIL_INFO_NON ;
//        }
//        if(StringUtils.isEmpty(Article.getName()) || StringUtils.isEmpty(Article.getPassword()) ||
//                 StringUtils.isEmpty(Article.getSex()) ||
//                StringUtils.isEmpty(Article.getEmail()) || StringUtils.isEmpty(Article.getPhone())) {
//            return REG_MSG_FAIL_INFO_NON ;
//        }
//        // 先进行账号是否存在检测
//        int nameC = articleDao.ArticleSearch(Article.getName()) ;
//        if(nameC > 0) {
//            return REG_MSG_FAIL_NAMEEXISTS ;
//        }
//        // 注册
//        Article.setPassword(DigestUtils.md5DigestAsHex(Article.getPassword().getBytes()));
////        Article.setAge(Article.getAge());
//        Article.setSex(Article.getSex());
//        Article.setEmail(Article.getEmail());
//        Article.setPhone(Article.getPhone());
////        Article.setPhotourl(Article.getPhotourl());
////        Article.setRole(Article.getRole());
////        System.out.println(Article);
//        int r = articleDao.addRole(Article) ;
//        if(r > 0) {
//            return REG_MSG_OK ;
//        } else {
//            return REG_MSG_FAIL_OTHER ;
//        }    }
//
//    @Override
//    public String getPhotoUrl(String name) {
//        return articleDao.getPhotoUrl(name);
//    }

}
