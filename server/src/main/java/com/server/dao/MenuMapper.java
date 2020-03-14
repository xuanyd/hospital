package com.server.dao;

import com.server.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    List<Menu> selectByIdsAndParent(Map<String, Object> params);
}
