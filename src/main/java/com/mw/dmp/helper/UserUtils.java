package com.mw.dmp.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户信息工具类
 * @author zangsh
 */
@Component
public class UserUtils {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取当前用户 token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        return request.getAttribute("token").toString();
    }

    /**
     * 获取当前用户 userId
     * @param request
     * @return
     */
    public String getUserId(HttpServletRequest request){
        return redisUtils.get(getToken(request)).toString();
    }

    /**
     * 根据当前用户的token获取userId管理username获取userInfo
     * @param request
     * @return  用户信息json string
     */
    public String getCurrentUserInfo(HttpServletRequest request){
        return redisUtils.get(getUserId(request)).toString();
    }
}
