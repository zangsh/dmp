package com.mw.dmp.controller;

import com.mw.dmp.helper.FastJsonUtils;
import com.mw.dmp.helper.ResultUtils;
import com.mw.dmp.helper.UserUtils;
import com.mw.dmp.service.ISysMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统菜单相关
 * @author zangsh
 */
@RestController
@RequestMapping(value = "/menu",produces = MediaType.APPLICATION_JSON_VALUE)
public class SysMenu {

    private static final Logger logger = LoggerFactory.getLogger(SysMenu.class);

    @Autowired
    private ISysMenu iSysMenu;

    @Autowired
    private UserUtils userUtils;
    /**
     * 获取用户菜单列表
     * 生成菜单树
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMenuList",method = RequestMethod.GET)
    public String getMenuList(String json, HttpServletRequest request){
        Map<String,Object> params = FastJsonUtils.stringToMap(json);
        String userId = userUtils.getUserId(request);
        params.put("userId",userId);
        //获取用户角色拥有的菜单
        List<Map<String,Object>> list = iSysMenu.getMenuList(params);
        logger.info("用户" + userId + " :拥有的菜单 " + list + "");
        List<Map<String,Object>> treeList = getTree(list,userId);
        logger.info("用户菜单树：" + ResultUtils.OK(treeList));
        return ResultUtils.OK(treeList);
    }

    /**
     * 递归方法 获取 菜单的级联关系
     * @param list
     * @return
     */
    public List<Map<String,Object>> getTree(List<Map<String,Object>> list,String userId){
       List<Map<String,Object>> menuLists = new ArrayList<>();
       Iterator<Map<String,Object>> iterator = list.iterator();
       while (iterator.hasNext()){
           Map<String,Object> menuMap = iterator.next();
           menuLists.add(menuMap);
           String menuId = menuMap.get("menu_id").toString();
           Map<String,Object> params = new HashMap<>();
           params.put("menuId",menuId);
           params.put("userId",userId);
           List<Map<String,Object>> childMenuList = iSysMenu.getMenuList(params);
           if (childMenuList.size() > 0){
               menuMap.put("chilren",childMenuList);
               getTree(childMenuList,userId);
           }
       }
        return menuLists;
    }
}
