package com.server.controller;


import com.server.entity.Menu;
import com.server.service.MenuService;
import com.server.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class IndexController {


    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public Object menus(){
        Map<String, Object> retMap = new HashMap<>();
        List<Menu> treeMenus = menuService.selectTreeMenus();
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("dataList", treeMenus);
        return retMap;
    }

}
