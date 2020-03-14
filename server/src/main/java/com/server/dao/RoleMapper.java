package com.server.dao;

import com.server.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    List<Role> selectByParams(Map<String, Object> params);
}
