package com.server.admin.controller;

import com.github.pagehelper.PageInfo;
import com.server.admin.service.NoticeService;
import com.server.entity.Notice;
import com.server.entity.User;
import com.server.util.PageRequest;
import com.server.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "notice/list", method = RequestMethod.POST)
    public Map getNoticePage(@RequestBody PageRequest pageQuery, String title) {
        Map retMap = new HashMap();
        PageInfo noticeInfo =  noticeService.getNoticePage(pageQuery, title);
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("data", noticeInfo);
        return retMap;
    }

}
