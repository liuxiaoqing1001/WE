package com.example.liu.weidea.service;

import com.example.liu.weidea.entity.*;

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
    List<Volunteer> getAllVolunteer();

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
     * 查询审核通过的自愿者
     * @return
     */
    List<Volunteer> getV();

    /**
     * 查询审核中的自愿者
     * @return
     */
    List<Volunteer> getNotV();

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    List<Volunteer> getVolunteer(String keyword);

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    List<User> getAdmin(String keyword);

    /**
     * 根据vid获取
     * @param vid
     * @return
     */
    List<Consultants> getConsultantsById(Integer vid);

    /**
     * 查询数据库中所有管理员数量
     * @return
     */
    Integer getAdminNum();

    /**
     * 查询数据库中所有用户（除管理员）数量
     * @return
     */
    Integer getUserNum();

    /**
     * 查询数据库中所有自愿者数量
     * @return
     */
    Integer getVolunteerNum();

    /**
     * 根据name获取自愿者相关信息
     * @param name
     * @return
     */
    Volunteer getVolunteerByName(String name);

    /**
     * 修改自愿者信息
     * @param volunteer
     * @return
     */
    Volunteer updateVByName(Volunteer volunteer);

    /**
     * 根据id查询身份
     * @param id
     * @return
     */
    String getRoleById(Integer id);

    /**
     * 修改自愿者状态
     * @param volunteer
     * @return
     */
    Volunteer updateVSateByName(Volunteer volunteer);

    /**
     * 提交自愿者申请
     * @param volunteer
     * @return
     */
    Integer addVRequest(Volunteer volunteer);

    /**
     * 根据name从用户表中获取sender(sex)/birth(birthday)/phoneNum(phone)
     * @param name
     * @return
     */
    Volunteer getUserByName(String name);

    /**
     * （树洞）发送say
     * @param say
     * @return
     */
    Integer sendSay(Say say);

    /**
     * （树洞）随机获取say
     * @return
     */
    Say getRandSay();

    /**
     * 根据用户id获取消息
     * @param receiver
     * @return
     */
    List<Comment> getMsgList(String receiver);

    /**
     * 获取最后一条自愿者申请数据
     * @return
     */
    String getLastVolunteerData();

//    /**
//     *Volunteer数量
//     * @return
//     */
//    int getVCount();

    /**
     *根据时间查询自愿者申请
     * @param time
     * @return
     */
    List<Volunteer> getVolunteerByTime(String time);

    /**
     * 根据时间查询审核通过的自愿者申请
     * @param time
     * @return
     */
    List<Volunteer> getVolunteerPassByTime(String time);

    /**
     * 获取最后一条数据
     * @return
     */
    String getLastSayData();

    /**
     * 根据时间查询
     * @param time
     * @return
     */
    List<Say> getSayByTime(String time);


//    /**
//     * 审核自愿者
//     * @param id
//     * @return
//     */
//    int updateVState(String id);
}
