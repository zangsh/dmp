package com.mw.dmp;

import com.mw.dmp.helper.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/sayHello")
    public String sayHello(){
        Object obj = null;
        if (redisUtils.exists("aktest")){
            obj = redisUtils.get("aktest");
            HashMap map = (HashMap) obj;
            for (Object key : map.keySet()){
                System.out.println(key + ":" +map.get(key));
            }
        }
        return "Hello SpringBoot!" + obj;
    }

    @RequestMapping("/redisHandler")
    public String redisHandler(){
        stringRedisTemplate.opsForValue().set("k5", "Springboot redis");
        return stringRedisTemplate.opsForValue().get("k5");
    }
}
