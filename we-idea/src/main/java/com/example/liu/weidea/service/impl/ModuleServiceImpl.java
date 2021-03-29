package com.example.liu.weidea.service.impl;

import com.example.liu.weidea.dao.ModuleDao;
import com.example.liu.weidea.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleDao moduleDao;

}
