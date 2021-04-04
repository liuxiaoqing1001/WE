package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.TypeDao;
import com.example.liu.weidea.entity.Type;
import com.example.liu.weidea.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeDao typeDao;

    /**
     * 获取所有类型
     * @return
     */
    @Override
    public List<Type> getAll() {
        return typeDao.getAll();
    }
}
