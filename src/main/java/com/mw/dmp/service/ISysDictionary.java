package com.mw.dmp.service;

import java.util.List;
import java.util.Map;

public interface ISysDictionary {
    /**
     * 获取图表样式字典项
     * @param params
     * @return
     */
    List<Map<String, Object>> getEchartCode(Map<String, Object> params);

    /**
     * 获取公司字典项
     * @param params
     * @return
     */
    List<Map<String, Object>> getCompany(Map<String, Object> params);
}
