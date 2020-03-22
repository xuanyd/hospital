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
    public PageInfo getNoticePage(int pageNum, int pageSize, String title) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> notices = noticeMapper.selectPage();
        return new PageInfo<Notice>(notices);
    }
}
