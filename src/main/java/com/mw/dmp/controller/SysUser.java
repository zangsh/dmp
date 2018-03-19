package com.mw.dmp.controller;

import com.mw.dmp.constants.ConstantsField;
import com.mw.dmp.helper.FastJsonUtils;
import com.mw.dmp.helper.RedisUtils;
import com.mw.dmp.helper.ResultUtils;
import com.mw.dmp.helper.TokenUtils;
import com.mw.dmp.service.ISysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户相关
 */
@RestController
@RequestMapping("/sys")
public class SysUser {

    private static Logger logger = LoggerFactory.getLogger(SysUser.class);
    @Autowired
    private ISysUser iSysUser;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 用户登陆校验获取相应信息
     * @param json
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postUserLogin",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String postUserLogin(@RequestBody String json, HttpServletRequest request){
        logger.info("getUserLogin参数：" + json);
        Map<String,Object> map = FastJsonUtils.stringToMap(json);
        Map<String,Object> data = iSysUser.getUserInfo(map);
        //合法用户获取token存储redis
        if (!data.isEmpty()){
            String userId = data.get("user_id").toString();
            String token = TokenUtils.encode(map);
            redisUtils.set(token,userId, ConstantsField.REDIS_EXPIRETIME);
            data.put("token",token);
            logger.info("redis缓存成功：token=" + token + ";userId" + userId);
        }
        return ResultUtils.OK(data);
    }
}
