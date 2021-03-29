package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.ModuleDao;
import com.example.liu.weidea.entity.Module;
import com.example.liu.weidea.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleDao moduleDao;

    /**
     * 添加模块
     * @param module
     * @return
     */
    @Override
    public Integer add(Module module) {
        if(null == module) {
            return REG_MSG_FAIL_INFO_NON ;
        }
        // 先进行账号是否存在检测
        int count = moduleDao.moduleSearch(module.getName()) ;
        if(count > 0) {
            return REG_MSG_FAIL_NAMEEXISTS ;
        }
        module.setName(module.getName());
        module.setRoute(module.getRoute());
        module.setText(module.getText());
        int r = moduleDao.add(module) ;
        if(r > 0) {
            return REG_MSG_OK ;
        } else {
            return REG_MSG_FAIL_OTHER ;
        }
    }

    /**
     * 更新
     * @param module
     * @return
     */
    @Override
    public Module update(Module module) {
        if(null == module) {
            return null ;
        }
        if(module.getName() == null) {
            return null ;
        }
        int r = moduleDao.updateById(module) ;
        if(r != 1) {
            return null ;
        } else {
            return moduleDao.getModuleById(module.getId()) ;
        }
    }

 
}
