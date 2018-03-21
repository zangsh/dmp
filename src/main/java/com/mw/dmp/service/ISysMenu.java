package com.mw.dmp.service;

import java.util.List;
import java.util.Map;

public interface ISysMenu {

    List<Map<String,Object>> getMenuList(Map<String,Object> params);
}
