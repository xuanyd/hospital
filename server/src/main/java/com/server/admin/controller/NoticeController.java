package com.server.admin.controller;

import com.github.pagehelper.PageInfo;
import com.server.admin.service.NoticeService;
import com.server.entity.Notice;
import com.server.entity.User;
import com.server.util.PageRequest;
import com.server.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "notice/list", method = RequestMethod.POST)
    public Map getNoticePage(
            @RequestParam(value = "page", required = true, defaultValue="1") Integer page,
            @RequestParam(value = "pageSize", required = true, defaultValue="10") Integer pageSize,
            String title) {
        Map retMap = new HashMap();
        PageInfo noticeInfo =  noticeService.getNoticePage(page, pageSize, title);
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("data", noticeInfo);
        return retMap;
    }

}
