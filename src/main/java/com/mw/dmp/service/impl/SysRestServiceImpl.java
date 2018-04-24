package com.mw.dmp.service.impl;

import com.mw.dmp.dao.ISysRestServiceDao;
import com.mw.dmp.service.ISysRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRestServiceImpl implements ISysRestService {

    @Autowired
    private ISysRestServiceDao iSysRestServiceDao;
    @Override
    public List<Map> getRestServiceList(Map params) {
        return iSysRestServiceDao.getRestServiceList(params);
    }

    @Override
    public Map getRestServiceByCode(String code) {
        return iSysRestServiceDao.getRestServiceByCode(code);
    }
}
