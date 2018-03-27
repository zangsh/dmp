package com.mw.dmp.filter;

import com.mw.dmp.constants.ConstantsField;
import com.mw.dmp.helper.RedisUtils;
import com.mw.dmp.helper.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 解决跨域访问
 * 过滤未授权访问
 */
@Order(value = 1)
@WebFilter(urlPatterns = "/*")
public class CorsFilter implements Filter {

    @Autowired
    private RedisUtils redisUtils;

    @Value("${notfilter}")
    private String notfilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        //, GET, OPTIONS, DELETE
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,JSESSIONID");
        String uri = request.getRequestURI();
        //非登录的请求，判断用户是否已经登陆，根据是否存在正确的token
        if(uri.indexOf(notfilter) == -1) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for (Cookie cookie : cookies) {
                    //如果用户合法，刷新token过期时间
                    if (ConstantsField.COOKIE_NAME.equals(cookie.getName())){
                        if (redisUtils.exists(cookie.getValue())){
                            request.setAttribute("token",cookie.getValue());
                            redisUtils.set(cookie.getValue(),redisUtils.get(cookie.getValue()),ConstantsField.REDIS_EXPIRETIME);
                        }else {
                            response.getWriter().write(ResultUtils.ERROR(501,"错误的令牌！",null));
                            return;
                        }
                    }else {
                        response.getWriter().write( ResultUtils.ERROR(502,"错误的令牌名！",null));
                        return;
                    }
                }
            }else {
                response.getWriter().write( ResultUtils.ERROR(503,"令牌不存在！",null));
                return;
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
