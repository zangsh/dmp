package com.mw.dmp.controller;

import com.mw.dmp.helper.FastJsonUtils;
import com.mw.dmp.helper.ResultUtils;
import com.mw.dmp.service.ISysRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * rest_service操作控制器
 *
 */
@RestController
@RequestMapping(value = "/restService",produces = MediaType.APPLICATION_JSON_VALUE)
public class SysRestService {

    @Autowired
    private ISysRestService iSysRestService;

    @ResponseBody
    @RequestMapping(value = "/getRestServiceList",method = RequestMethod.POST)
    public String getRestServiceList(@RequestBody String json){
        Map params = FastJsonUtils.stringToMap(json);
        List<Map> list = iSysRestService.getRestServiceList(params);
        return ResultUtils.OK(list);
    }
}
