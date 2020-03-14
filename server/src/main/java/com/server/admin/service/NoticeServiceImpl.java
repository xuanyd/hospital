package com.server.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.dao.NoticeMapper;
import com.server.entity.Notice;
import com.server.util.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public PageInfo getNoticePage(PageRequest pageRequest, String title) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> notices = noticeMapper.findPage();
        return new PageInfo<Notice>(notices);
    }
}
