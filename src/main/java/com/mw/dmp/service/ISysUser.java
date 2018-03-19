package com.mw.dmp.service;

import java.util.Map;

public interface ISysUser {
    /**
     * 用户登陆判断及获取信息
     * @param params
     * @return
     */
    Map<String,Object> getUserInfo(Map<String,Object> params);
}
