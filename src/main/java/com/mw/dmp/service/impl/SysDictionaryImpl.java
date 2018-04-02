package com.mw.dmp.service.impl;

import com.mw.dmp.dao.SysDictionaryDao;
import com.mw.dmp.service.ISysDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysDictionaryImpl implements ISysDictionary {

    @Autowired
    private SysDictionaryDao sysDictionaryDao;

    @Override
    public List<Map<String, Object>> getEchartCode(Map<String, Object> params) {
        return sysDictionaryDao.getEchartCode(params);
    }
}
