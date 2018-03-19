package com.mw.dmp;

import com.mw.dmp.helper.FastJsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义响应
 */
public class ResultUtils {

    private static final Map<String,Object> MAP = new HashMap<>();

    private static final String CODE = "CODE";
    private static final String OK_CODE = "200";
    private static final String ERROR_CODE = "500";

    private static final String MSG = "MSG";
    private static final String OK_MSG = "操作成功！";
    private static final String ERROR_MSG = "操作失败！";

    private static final String DATA = "DATA";

    public static void main(String[] args) {
        System.out.println(OK("302","{name:zangsh}"));
    }

    public static String OK(){
        return OK(null);
    }

    public static String OK(String data){
        return OK(OK_CODE,data);
    }

    public static String OK(String code,String data){
        return OK(code,OK_MSG,data);
    }

    public static String OK(String code,String msg,String data){
        MAP.put(CODE,code);
        MAP.put(MSG,msg);
        MAP.put(DATA,data);
        return FastJsonUtils.mapToString(MAP);
    }


    public static String ERROR(){
        return ERROR(null);
    }

    public static String ERROR(String data){
        return ERROR(ERROR_CODE,data);
    }

    public static String ERROR(String code,String data){
        return ERROR(code,ERROR_MSG,data);
    }

    public static String ERROR(String code,String msg,String data){
        MAP.put(CODE,code);
        MAP.put(MSG,msg);
        MAP.put(DATA,data);
        return FastJsonUtils.mapToString(MAP);
    }
}
