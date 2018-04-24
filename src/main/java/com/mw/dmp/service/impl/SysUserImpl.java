package com.mw.dmp.service.impl;

import com.mw.dmp.dao.SysUserDao;
import com.mw.dmp.service.ISysUser;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserImpl implements ISysUser {
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public Map<String, Object> getUserInfo(Map<String, Object> params) {
        String password = params.get("password").toString();
        // sha256加密
        password = new Sha256Hash(password).toHex();
        params.put("password",password);
        return sysUserDao.getUserInfo(params);
    }

    @Override
    public Map getCurrentUserInfoForId(String userId) {
        return sysUserDao.getCurrentUserInfoForId(userId);
    }
}
