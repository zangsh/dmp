package com.mw.dmp.service;

import java.util.List;
import java.util.Map;

public interface ISysRestService {
    /**
     * 获取注册服务列表
     * @param params
     * @return
     */
    List<Map> getRestServiceList(Map params);

    /**
     * 根据code查询提供服务的url
     * @param code
     * @return
     */
    Map getRestServiceByCode(String code);
}
