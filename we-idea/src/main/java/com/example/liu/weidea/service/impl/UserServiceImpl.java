package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.*;
import com.example.liu.weidea.entity.*;
import com.example.liu.weidea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    ConsultantsDao consultantsDao;

    @Autowired
    VolunteerDao volunteerDao;

    @Autowired
    SayDao sayDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    ArticleDao articleDao;

    /**
     * 登录
     * @param phone  非空
     * @param pwd  非空
     * @return
     */
    @Override
    public Map<String, Object> loginCheck(String phone, String pwd) {
        Map<String , Object> map = new HashMap<>() ;
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)) {
            map.put(KEY_MSG , LOGIN_MSG_FAIL_OTHER) ;
            return map ;
        }
        // 先进行账号是否存在检测
        int nameCount = userDao.userSearch(phone) ;
        if(nameCount == 0) {
            map.put(KEY_MSG , LOGIN_MSG_FAIL_NON) ;
            return map ;
        }
        // 账号存在，登录检查
        User u = userDao.select(phone , DigestUtils.md5DigestAsHex(pwd.getBytes())) ;
        if(u == null) {
            map.put(KEY_MSG , LOGIN_MSG_FAIL_ERROR) ;
            return map ;
        }
        map.put(KEY_USER , u) ;
        map.put(KEY_MSG , LOGIN_MSG_OK) ;
        if (u.getRole() == 0) {
            map.put(KEY_ROLE , ROLE_ADMIN) ;
        } else if (u.getRole() == 1) {
            map.put(KEY_ROLE , ROLE_USER) ;
        }
        return map;
    }

    /**
     * 注册
     * @param user 非空，电话和密码非空
     * @return
     */
    @Override
    public Integer register(User user) {
        if(null == user) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        if(StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(user.getPhone()) ) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        // 先进行账号是否存在检测
        int count = userDao.userSearch(user.getPhone()) ;
        if(count > 0) {
            return REG_MSG_FAIL_NAMEEXISTS ;
        }
        // 注册
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setPhone(user.getPhone());
        int r = userDao.add(user) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }

    /**
     * 添加管理员
     * @param user 非空，电话和密码非空
     * @return
     */
    @Override
    public Integer registerAdmin(User user) {
        if( user == null ) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        if(StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(user.getPhone()) ) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        // 先进行账号是否存在检测
        int count = userDao.userSearch(user.getPhone()) ;
        if(count > 0) {
            return REG_MSG_FAIL_NAMEEXISTS ;
        }
        // 注册
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setPhone(user.getPhone());
        user.setRole(user.getRole());
        int r = userDao.addAdmin(user) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }

    /**
     * 根据电话号查询用户信息
     * @param phone
     * @return
     */
    @Override
    public User searchByPhone(String phone) {
        if(phone==""||phone==null){
            return null;
        }
        return userDao.searchByPhone(phone);
    }

    /**
     * 修改秘密
     * @param password
     * @param phone
     * @return
     */
    @Override
    public Integer modifyPwd(String password, String phone) {
        if(phone==null||phone==""){
            return null;
        }
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        return userDao.modifyPwd(password, phone);
    }

    /**
     * 查询数据库中所有管理员
     * @return
     */
    @Override
    public List<User> getAllAdmin() {
        return userDao.getAllAdmin();
    }

    /**
     * 查询数据库中所有用户（除管理员）
     * @return
     */
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    /**
     * 查询数据库中所有自愿者
     * @return
     */
    @Override
    public List<User> getAllVolunteer() {
        return userDao.getAllVolunteer();
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @Override
    public User update(User user) {
        if(null == user) {
            return null ;
        }
        if(user.getId() == null || user.getId() < 1) {
            return null ;
        }
        if(null != user.getPassword()) {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        int r = userDao.updateById(user) ;
        if(r != 1) {
            return null ;
        } else {
            return userDao.getUserById(user.getId()) ;
        }
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    /**
     * 根据关键字查询用户
     * @param keyword
     * @return
     */
    @Override
    public List<User> getUser(String keyword) {
        if(keyword==""||keyword==null){
            return userDao.getAllUser();
        }
        return userDao.getUser(keyword);
    }

    /**
     * 根据关键字查询自愿者
     * @param keyword
     * @return
     */
    @Override
    public List<User> getVolunteer(String keyword) {
        if(keyword==""||keyword==null){
            return userDao.getAllVolunteer();
        }
        return userDao.getVolunteer(keyword);
    }

    /**
     * 根据关键字查询管理员
     * @param keyword
     * @return
     */
    @Override
    public List<User> getAdmin(String keyword) {
        if(keyword==""||keyword==null){
            return userDao.getAllAdmin();
        }
        return userDao.getAdmin(keyword);
    }

    /**
     * 根据vid获取
     * @param vid
     * @return
     */
    @Override
    public List<Consultants> getConsultantsById(Integer vid) {
        return consultantsDao.getConsultantsById(vid);
    }

    /**
     * 获取管理员数量
     * @return
     */
    @Override
    public Integer getAdminNum() {
        return userDao.getAdminNum();
    }

    /**
     * 获取用户数量
     * @return
     */
    @Override
    public Integer getUserNum() {
        return userDao.getUserNum();
    }

    /**
     * 获取自愿者数量
     * @return
     */
    @Override
    public Integer getVolunteerNum() {
        return userDao.getVolunteerNum();
    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    /**
     * 根据name获取自愿者相关信息
     * @param name
     * @return
     */
    @Override
    public Volunteer getVolunteerByName(String name) {
        Volunteer v = volunteerDao.getVolunteerByName(name);
        User u = userDao.getUserByName(name);
        v.setBirth(u.getBirthday());
        v.setSender(u.getSex());
        v.setPhoneNum(u.getPhone());
        return v;
    }

    /**
     * 修改自愿者信息
     * @param v
     * @return
     */
    @Override
    public Volunteer updateVByName(Volunteer v) {
        if(null == v) {
            return null ;
        }
        User user = new User();
        user.setName(v.getName());
        user.setSex(v.getSender());
//        user.setBirthday(v.getBirth());
        user.setPhone(v.getPhoneNum());
        int r1 = volunteerDao.updateVByName(v) ;
        int r2 = userDao.updateByName(user) ;
        if(r1 != 1&&r2!=1) {
            return null ;
        } else {
            return volunteerDao.getVolunteerByName(v.getName()) ;
        }
    }

    /**
     * 根据id查询身份
     * @param id
     * @return
     */
    @Override
    public String getRoleById(Integer id) {
        return userDao.getRoleById(id);
    }

    /**
     * 修改自愿者状态
     * @param volunteer
     * @return
     */
    @Override
    public Volunteer updateVSateByName(Volunteer volunteer) {
        if(null == volunteer) {
            return null ;
        }
        int r1 = volunteerDao.updateVByName(volunteer) ;
        if(r1 != 1) {
            return null ;
        } else {
            return volunteerDao.getVolunteerByName(volunteer.getName()) ;
        }
    }

    /**
     * 提交自愿者申请
     * @param volunteer
     * @return
     */
    @Override
    public Integer addVRequest(Volunteer volunteer) {
        if(null == volunteer) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        // 先进行账号是否存在检测
        int count = volunteerDao.volunteerSearch(volunteer.getIdentity()) ;
        if(count > 0) {
            return REG_MSG_FAIL_NAMEEXISTS ;
        }
        // 提交自愿者申请
        User user = new User();
        user.setName(volunteer.getName());
        user.setSex(volunteer.getSender());
//        user.setBirthday(v.getBirth());
        user.setPhone(volunteer.getPhoneNum());
        int r1 = userDao.updateByName(user);
        int r2 = volunteerDao.addVRequest(volunteer) ;
        if(r2 > 0&&r1>0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }

    /**
     * 根据name从用户表中获取sender(sex)/birth(birthday)/phoneNum(phone)
     * @param name
     * @return
     */
    @Override
    public Volunteer getUserByName(String name) {
        User user = userDao.getUserByName(name);
        Volunteer volunteer = new Volunteer();
        volunteer.setName(name);
        volunteer.setSender(user.getSex());
        volunteer.setBirth(user.getBirthday());
        volunteer.setPhoneNum(user.getPhone());
        return volunteer;
    }

    /**
     * （树洞）发送say
     * @param say
     * @return
     */
    @Override
    public Integer sendSay(Say say) {
        if(null == say) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        // 发送
        int r = sayDao.add(say) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }

    /**
     * （树洞）随机获取say
     * @return
     */
    @Override
    public Say getRandSay() {
        return sayDao.getRandSay();
    }

    /**
     * 根据用户id获取消息
     * @param receiver
     * @return
     */
    @Override
    public List<Comment> getMsgList(String receiver) {
        List<Comment> comments = commentDao.getMsgList(receiver);
        for (int i=0;i<comments.size();i++){
            if(comments.get(i).getSid()==null){
                String msgContent = articleDao.getByAid(comments.get(i).getAid());
                comments.get(i).setMsgContent(msgContent);
            }else {
                String msgContent = sayDao.getByAid(comments.get(i).getSid());
                comments.get(i).setMsgContent(msgContent);
            }
        }
        return comments;
    }
}
