package com.mw.dmp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

public interface SysMenuDao {

    @Cacheable(value = "dmp",key = "#p0['userId'].toString() + #p0['menuId'].toString()")
    List<Map<String,Object>> getMenuList(@Param(value = "params") Map<String,Object> params);
}
