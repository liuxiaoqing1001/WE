package com.example.liu.weidea.service;


public interface ArticleService {
    public static final int REG_MSG_OK = 0 ;
    public static final int REG_MSG_FAIL_NAMEEXISTS = 1 ;
    public static final int REG_MSG_FAIL_INFO_NON = 2 ;
    public static final int REG_MSG_FAIL_OTHER = 3 ;

    String KEY_Article = "Article" ;
    String KEY_MSG = "Msg" ;
    String KEY_ROLE = "Role" ;
    int LOGIN_MSG_OK = 0 ;
    int LOGIN_MSG_FAIL_NON = 1 ;
    int LOGIN_MSG_FAIL_ERROR = 2 ;
    int LOGIN_MSG_FAIL_OTHER = 3 ;
    int ROLE_ADMIN = 0 ;
    int ROLE_Article = 1 ;

//    /**
//     * 登录
//     * @param phone  非空
//     * @param pwd  非空
//     * @return
//     */
//    Map<String , Object> loginCheck(String phone, String pwd) ;
//
//    /**
//     * 注册
//     * @param Article
//     * @return Integer
//     */
//    Integer register(Article Article) ;
//
//    /**
//     * 根据id修改个人信息
//     * @param Article
//     * @return
//     */
//    Article update(Article Article) ;
//
//    /**
//     * 分页
//     * @param id
//     * @param name
//     * @param regdate
//     * @param curPage
//     * @param size
//     * @return
//     */
//    Page<Article> newsPage(Integer id, String name, Date regdate, Integer curPage, Integer size) ;
//
//    List<Article> getAll() ;
//
//    /**
//     * 根据id获取用户信息
//     * @param id
//     * @return
//     */
//    Article getArticleById(Integer id) ;
//
//    /**
//     * 根据id删除用户信息
//     * @param id
//     * @return
//     */
//    int deleteById(Integer id) ;
//
//    Integer upRole(Integer id);
//
//    Integer delById(Integer id);
//
//    Page<Article> RolePage(Integer curPage, Integer size);
//
//    Integer addRole(Article Article);
//
//    String getPhotoUrl(String name);
//
//    /**
//     * 根据电话号查询用户信息
//     * @param phone
//     * @return
//     */
//    Article searchByPhone(String phone);
//
//    /**
//     * 修改密码
//     * @param password
//     * @param phone
//     * @return
//     */
//    Integer modifyPwd(String password, String phone);
//
//    /**
//     * 查询数据库中所有管理员
//     * @return
//     */
//    List<Article> getAllAdmin();
//
//    /**
//     * 查询数据库中所有用户（除管理员）
//     * @return
//     */
//    List<Article> getAllArticle();
//
//    /**
//     * 查询数据库中所有自愿者
//     * @return
//     */
//    List<Article> getAllVolunteer();
//
//    /**
//     * 添加管理员
//     * @param Article
//     * @return
//     */
//    Integer registerAdmin(Article Article);
//
//    /**
//     * 根据关键字查询用户
//     * @param keyword
//     * @return
//     */
//    List<Article> getArticle(String keyword);
//
//    /**
//     * 根据关键字查询自愿者
//     * @param keyword
//     * @return
//     */
//    List<Article> getVolunteer(String keyword);
//
//    /**
//     * 根据关键字查询自愿者
//     * @param keyword
//     * @return
//     */
//    List<Article> getAdmin(String keyword);
}
