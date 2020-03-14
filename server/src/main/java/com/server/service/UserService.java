package com.server.service;

import com.github.pagehelper.PageInfo;
import com.server.entity.User;
import com.server.util.PageRequest;
import com.server.util.PageResult;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getByNameAndPwd(String username, String md5Str);

    boolean deleteById(Integer id);

    User selectById(Integer id);

    boolean editNameAndUsername(int userId, String name, String username);

    PageInfo findPage(PageRequest pageRequest);
}
