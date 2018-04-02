package com.mw.dmp.controller;

import com.mw.dmp.constants.ConstantsField;
import com.mw.dmp.helper.FastJsonUtils;
import com.mw.dmp.helper.ResultUtils;
import com.mw.dmp.helper.TokenUtils;
import com.mw.dmp.service.ISysDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 字典相关接口集合
 * @author zangsh
 */
@RestController
@RequestMapping(value = "/dictionary",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
public class SysDictionary {

    private static Logger logger = LoggerFactory.getLogger(SysDictionary.class);
    @Autowired
    private ISysDictionary iSysDictionary;

    @ResponseBody
    @RequestMapping(value = "/getEchartCode")
    public String getEchartCode(@RequestBody String json){
        logger.info("getEchartCode参数：" + json);
        Map<String,Object> map = FastJsonUtils.stringToMap(json);
        if (!map.containsKey("code")){
            return ResultUtils.ERROR("不正确的参数！");
        }
        List<Map<String, Object>> data = iSysDictionary.getEchartCode(map);
        return ResultUtils.OK(data);
    }
}
