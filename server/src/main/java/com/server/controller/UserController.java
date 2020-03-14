package com.server.controller;


import com.server.entity.User;
import com.server.service.UserService;
import com.server.util.PageRequest;
import com.server.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map getUsers(@RequestBody Map<String, String> body) {
        int page = Integer.valueOf(body.get("page"));
        int pageSize = Integer.valueOf(body.get("pageSize"));
        Map retMap = new HashMap();
        List<User> users =  userService.getUsers();
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("dataList", users);
        return retMap;
    }

    @RequestMapping(value = "/list-page", method = RequestMethod.POST)
    public Map getUserPage(@RequestBody PageRequest pageQuery) {
        Map retMap = new HashMap();
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("data", userService.findPage(pageQuery));
        return retMap;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Map getUser(@PathVariable Integer id) {
        Map retMap = new HashMap();
        User user =  userService.selectById(id);
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("detail", user);
        return retMap;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Map editUser(@RequestBody Map<String, String> body) {
        Map retMap = new HashMap();
        int userId = Integer.valueOf(body.get("id"));
        String name = body.get("name");
        String username = body.get("username");
        boolean editFlag = userService.editNameAndUsername(userId, name, username);
        if(editFlag)
            retMap.put("code", ResultCode.SUCCESS);
        else
            retMap.put("code", ResultCode.FAIL);
        return retMap;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Map deleteUser(@PathVariable Integer id) {
        Map retMap = new HashMap();
        boolean deleteFlag =  userService.deleteById(id);
        if(deleteFlag)
            retMap.put("code", ResultCode.SUCCESS);
        else
            retMap.put("code", ResultCode.FAIL);
        return retMap;
    }




}
