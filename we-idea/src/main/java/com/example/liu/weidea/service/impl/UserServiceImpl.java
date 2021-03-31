package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.UserDao;
import com.example.liu.weidea.entity.User;
import com.example.liu.weidea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

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











    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Integer upRole(Integer id) {
        if(id == null || id<1) {
            return null ;
        }
        int r = userDao.upRole(id) ;
        if(r != 1) {
            return null ;
        } else {
            return userDao.upRole(id) ;
        }
    }

    @Override
    public Integer delById(Integer id) {
        if(id == null || id<1) {
            return null ;
        }
        int r = userDao.delById(id) ;
        if(r != 1) {
            return null ;
        } else {
            return userDao.delById(id) ;
        }
    }



    @Override
    public Integer addRole(User user) {
//        System.out.println(user.toString());
        if(null == user) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        if(StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword()) ||
                 StringUtils.isEmpty(user.getSex()) ||
                StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPhone())) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        // 先进行账号是否存在检测
        int nameC = userDao.userSearch(user.getName()) ;
        if(nameC > 0) {
            return REG_MSG_FAIL_NAMEEXISTS ;
        }
        // 注册
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
//        user.setAge(user.getAge());
        user.setSex(user.getSex());
        user.setEmail(user.getEmail());
        user.setPhone(user.getPhone());
//        user.setPhotourl(user.getPhotourl());
//        user.setRole(user.getRole());
//        System.out.println(user);
        int r = userDao.addRole(user) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }    }

    @Override
    public String getPhotoUrl(String name) {
        return userDao.getPhotoUrl(name);
    }

}
