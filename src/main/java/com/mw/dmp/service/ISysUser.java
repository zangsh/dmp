package com.mw.dmp.service;

import java.util.Map;

public interface ISysUser {
    /**
     * 用户登陆判断及获取信息
     * @param params  用户名和密码
     * @return
     */
    Map<String,Object> getUserInfo(Map<String,Object> params);

    /**
     * 根据用户ID获取用户信息
     * @param userId  用户ID
     * @return
     */
    Map getCurrentUserInfoForId(String userId);
}
