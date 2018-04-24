package com.mw.dmp.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * 常量类
 * @author zangsh
 */
public class ConstantsField {
    //redis key过期时间设置
    public static final Long REDIS_EXPIRETIME = 1800L;

    //设置cookie名
    public static final String COOKIE_NAME = "dmp";

    @Value("#{info.mesBdpBaseRestUrl}")
    public String mesBdpBaseRestUrl;
}
