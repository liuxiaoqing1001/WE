package com.example.liu.weidea.service;

import com.example.liu.weidea.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface UserService {
    public static final int REG_MSG_OK = 0 ;
    public static final int REG_MSG_FAIL_NAMEEXISTS = 1 ;
    public static final int REG_MSG_FAIL_INFO_NON = 2 ;
    public static final int REG_MSG_FAIL_OTHER = 3 ;

    String KEY_USER = "User" ;
    String KEY_MSG = "Msg" ;
    String KEY_ROLE = "Role" ;
    int LOGIN_MSG_OK = 0 ;
    int LOGIN_MSG_FAIL_NON = 1 ;
    int LOGIN_MSG_FAIL_ERROR = 2 ;
    int LOGIN_MSG_FAIL_OTHER = 3 ;
    int ROLE_ADMIN = 0 ;
    int ROLE_USER = 1 ;

    /**
     * 登录
     * @param phone  非空
     * @param pwd  非空
     * @return
     */
    Map<String , Object> loginCheck(String phone, String pwd) ;

    /**
     * 注册
     * @param user
     * @return Integer
     */
    Integer register(User user) ;

    /**
     * 根据id修改个人信息
     * @param user
     * @return
     */
    User update(User user) ;

    List<User> getAll() ;

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id) ;

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    int deleteById(Integer id) ;

    Integer upRole(Integer id);

    Integer delById(Integer id);


    Integer addRole(User user);

    String getPhotoUrl(String name);

    /**
     * 根据电话号查询用户信息
     * @param phone
     * @return
     */
    User searchByPhone(String phone);

    /**
     * 修改密码
     * @param password
     * @param phone
     * @return
     */
    Integer modifyPwd(String password, String phone);

    /**
     * 查询数据库中所有管理员
     * @return
     */
    List<User> getAllAdmin();

    /**
     * 查询数据库中所有用户（除管理员）
     * @return
     */
    List<User> getAllUser();

    /**
     * 查询数据库中所有自愿者
     * @return
     */
    List<User> getAllVolunteer();

    /**
     * 添加管理员
     * @param user
     * @return
     */
    Integer registerAdmin(User user);

    /**
     * 根据关键字查询用户
     * @param keyword
     * @return
     */
    List<User> getUser(String keyword);

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    List<User> getVolunteer(String keyword);

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    List<User> getAdmin(String keyword);
}
