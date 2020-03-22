package com.server.dao;

import com.server.entity.Notice;
import com.server.util.PageRequest;
import com.server.util.PageResult;

import java.util.List;

public interface NoticeMapper {
    List<Notice> selectPage();
}
