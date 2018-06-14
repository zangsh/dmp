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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 系统用户相关
 */
@RestController
@RequestMapping("/user")
public class SysUser {

    private static Logger logger = LoggerFactory.getLogger(SysUser.class);
    @Autowired
    private ISysUser iSysUser;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * @api {post} http://localhost:8080/user/postLogin 用户登陆
     * @apiName 用户登陆
     * @apiGroup user-interface
     * @apiVersion 0.0.1
     * @apiDescription 用户登陆校验获取相应信息
     * @apiDescription 用于注册用户
     * @apiParam {String} account 用户账户名
     * @apiParam {String} password 密码
     * @apiParam {String} mobile 手机号
     * @apiParam {int} vip = 0  是否注册Vip身份 0 普通用户 1 Vip用户
     * @apiParam {String} [recommend] 邀请码
     * @apiParamExample {json} 请求样例：
     *                ?account=sodlinken&password=11223344&mobile=13739554137&vip=0&recommend=
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (200) {int} code 0 代表无错误 1代表有错误
     * @apiSuccessExample {json} 返回样例:
     *                {"code":"0","msg":"注册成功"}
     */
    @ResponseBody
    @RequestMapping(value = "/postLogin",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String postLogin(@RequestBody String json){
        logger.info("postLogin参数：" + json);
        Map<String,Object> map = FastJsonUtils.stringToMap(json);
        Map<String,Object> data = iSysUser.getUserInfo(map);
        //合法用户获取token存储redis
        if (!(data == null || data.isEmpty())){
            String userId = data.get("user_id").toString();
            String token = TokenUtils.encode(map);
            redisUtils.set(token,userId, ConstantsField.REDIS_EXPIRETIME);
            data.put("token",token);
            logger.info("redis缓存成功：token=" + token + ";userId" + userId);
        }
        return ResultUtils.OK(data);
    }

    /**
     * 用户登出
     * 将token从redis中移除
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getLogOut",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLogOut(HttpServletRequest request){
        String token = request.getAttribute("token").toString();
        redisUtils.remove(token);
        logger.info("用户 " + redisUtils.get(token) + " 退出！");
        return ResultUtils.OK();
    }
}
