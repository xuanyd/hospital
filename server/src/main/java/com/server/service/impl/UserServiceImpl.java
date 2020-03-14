package com.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.dao.UserMapper;
import com.server.entity.User;
import com.server.service.UserService;
import com.server.util.PageRequest;
import com.server.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.selectByParams(null);
    }

    @Override
    public User getByNameAndPwd(String username, String md5Str) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", md5Str);
        return userMapper.selectByUsernameAndPassword(params);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(userMapper.deleteById(id) == 1)
            return true;
        return false;
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean editNameAndUsername(int userId, String name, String username) {
        User user = new User();
        user.setId(userId);
        user.setName(name);
        user.setUsername(username);
        if(userMapper.editNameAndUsername(user) == 1)
            return true;
        return false;
    }

    @Override
    public PageInfo findPage(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<User> sysMenus = userMapper.selectPage();
        return new PageInfo<User>(sysMenus);
    }

}
