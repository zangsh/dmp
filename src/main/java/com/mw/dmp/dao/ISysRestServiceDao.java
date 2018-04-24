package com.mw.dmp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import redis.clients.jedis.params.Params;

import java.util.List;
import java.util.Map;

public interface ISysRestServiceDao {
    /**
     * 获取注册服务列表
     * @param params
     * @return
     */
    @Cacheable(value = "dmpRestService",key = "'getRestServiceList_' + #p0['name'].toString()")
    List<Map> getRestServiceList(@Param(value = "params") Map params);

    /**
     * 根据code查询提供服务的url
     * @param code
     * @return
     */
    @Cacheable(value = "dmpRestService")
    Map getRestServiceByCode(@Param(value = "code") String code);
}
