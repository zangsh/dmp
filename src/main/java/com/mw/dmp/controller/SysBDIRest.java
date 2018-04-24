package com.mw.dmp.controller;

import com.mw.dmp.helper.FastJsonUtils;
import com.mw.dmp.helper.UserUtils;
import com.mw.dmp.service.ISysRestService;
import com.mw.dmp.service.ISysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 请求数据服务接口
 */
@RestController
@RequestMapping(value = "/bdi")
public class SysBDIRest {

    private static Logger logger = LoggerFactory.getLogger(SysBDIRest.class);

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private ISysUser iSysUser;
    @Autowired
    private ISysRestService iSysRestService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${info.mesBdpBaseRestUrl}")
    private String mesBdpBaseRestUrl;
    @Value("${info.aokeBdpBaseRestUrl}")
    private String aokeBdpBaseRestUrl;
    @Value("${info.manbaoBdpBaseRestUrl}")
    private String manbaoBdpBaseRestUrl;

    @ResponseBody
    @RequestMapping(value = "/{bdpMappingCode}", produces="application/json;charset=UTF-8")
    public Object getBDP(@PathVariable String bdpMappingCode, HttpServletRequest request) {
        logger.info("bid_getBDP request参数:" + request + " bdpMappingCode=" + bdpMappingCode);
        String comapnyId = "0";
        String baseUrl = null;
        String userId = userUtils.getUserId(request);
        Map userInfoMap = iSysUser.getCurrentUserInfoForId(userId);
        if (userInfoMap.containsKey("company")){
            /**
             * 如果公司ID不存在，默认为mes
             * 北汽MES	0
             * 满宝	    1
             * 澳客	    2
             */
            comapnyId = StringUtils.isEmpty(userInfoMap.get("company")) ? "0" : userInfoMap.get("company").toString();
        }
        switch (comapnyId){
            case "0":
                baseUrl = mesBdpBaseRestUrl;
                break;
            case "1":
                baseUrl = aokeBdpBaseRestUrl;
                break;
            case "2":
                baseUrl = manbaoBdpBaseRestUrl;
                break;
        }
        Map restServiceMap = iSysRestService.getRestServiceByCode(bdpMappingCode);
        String strURL = restServiceMap.get("url").toString();
        String bdpRestUrl = baseUrl + strURL;
        List<String> keyLst = Collections.list(request.getParameterNames());
        if (keyLst.size() > 0)
        {
            StringBuffer sb = new StringBuffer();
            keyLst.forEach(key -> {
                try {
                    sb.append(key).append("=").append(new String(request.getParameter(key).getBytes("ISO-8859-1"), "utf-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    logger.error("请求服务SysBDIRest.getBDP发生错误:" + e.getMessage());
                }
            });
            bdpRestUrl += (org.apache.commons.lang.StringUtils.contains(bdpRestUrl, "?") ? "&" : "?") + sb.toString();
        }
        return restTemplate.getForObject(bdpRestUrl,Object.class);
    }
}
