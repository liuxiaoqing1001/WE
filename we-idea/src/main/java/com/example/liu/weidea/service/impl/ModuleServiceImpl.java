package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.ModuleDao;
import com.example.liu.weidea.entity.Module;
import com.example.liu.weidea.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        Integer sort = moduleDao.getLastSort();
//        module.setSort(sort);
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

    /**
     * 获取所有模块
     * @return
     */
    @Override
    public List<Module> getAll() {
        return moduleDao.getAll();
    }

    /**
     * 根据id删除模块
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return moduleDao.deleteById(id);
    }

    /**
     * 根据关键字查询模块
     * @param keyword
     * @return
     */
    @Override
    public List<Module> getModule(String keyword) {
        if(keyword==""||keyword==null){
            return moduleDao.getAll();
        }
        return moduleDao.getModule(keyword);
    }

    /**
     * 根据id获取模块信息
     * @param id
     * @return
     */
    @Override
    public Module getModuleById(Integer id) {
        return moduleDao.getModuleById(id);
    }

    /**
     * 根据id改变显示状态
     * @param id
     * @param state
     * @return
     */
    @Override
    public Integer updateState(Integer id, String state) {
        return moduleDao.updateState(id, state);
    }
}
