package com.mw.dmp.service.impl;

import com.mw.dmp.dao.SysMenuDao;
import com.mw.dmp.service.ISysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysMenuImpl implements ISysMenu {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public List<Map<String, Object>> getMenuList(Map<String, Object> params) {
        return sysMenuDao.getMenuList(params);
    }
}
