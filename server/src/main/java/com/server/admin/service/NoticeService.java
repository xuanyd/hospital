package com.server.admin.service;

import com.github.pagehelper.PageInfo;
import com.server.util.PageRequest;

public interface NoticeService {
    PageInfo getNoticePage(int pageNum, int pageSize, String title);
}
