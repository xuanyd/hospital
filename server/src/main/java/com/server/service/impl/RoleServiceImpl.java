package com.server.service.impl;

import com.server.dao.RoleMapper;
import com.server.entity.Role;
import com.server.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoles() {
        return roleMapper.selectByParams(null);
    }
}
