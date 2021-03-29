package com.example.liu.weidea.service;


import com.example.liu.weidea.entity.Module;

public interface ModuleService {
    public static final int REG_MSG_OK = 0 ;
    public static final int REG_MSG_FAIL_NAMEEXISTS = 1 ;
    public static final int REG_MSG_FAIL_INFO_NON = 2 ;
    public static final int REG_MSG_FAIL_OTHER = 3 ;

    String KEY_Module = "Module" ;
    String KEY_MSG = "Msg" ;
    int LOGIN_MSG_OK = 0 ;
    int LOGIN_MSG_FAIL_NON = 1 ;
    int LOGIN_MSG_FAIL_ERROR = 2 ;
    int LOGIN_MSG_FAIL_OTHER = 3 ;
    int ROLE_ADMIN = 0 ;
    int ROLE_Module = 1 ;

    /**
     * 添加模块
     * @param module
     * @return
     */
    Integer add(Module module);

    /**
     * 更新
     * @param module
     * @return
     */
    Module update(Module module);
}
