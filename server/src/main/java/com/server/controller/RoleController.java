package com.server.controller;

import com.server.entity.Role;
import com.server.service.RoleService;
import com.server.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map getUsers(@RequestBody Map<String, String> body) {
        int page = Integer.valueOf(body.get("page"));
        int pageSize = Integer.valueOf(body.get("pageSize"));
        Map retMap = new HashMap();
        List<Role> roles =  roleService.getRoles();
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("dataList", roles);
        return retMap;
    }

}
