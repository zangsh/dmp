package com.mw.dmp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

public interface SysDictionaryDao {
    /**
     * 获取图表样式字典项
     * 并且缓存信息
     * @param params
     * @return
     */
    @Cacheable(value = "dmpDictionary",key = "'echartcode_' + #p0['code'].toString()")
    List<Map<String,Object>> getEchartCode(@Param(value = "params") Map<String, Object> params);
}
