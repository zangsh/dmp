package com.mw.dmp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;
public interface SysUserDao {
    /**
     * 用户登陆判断及获取信息
     * 并且缓存用户信息 key为用户名
     * @param params
     * @return
     */
    @Cacheable(value = "dmp",key = "#p0['username'].toString()")
    Map<String,Object> getUserInfo(@Param(value = "params") Map<String,Object> params);
}
