package com.server.dao;

import com.server.entity.User;
import com.server.util.PageRequest;
import com.server.util.PageResult;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User selectById(Integer id);

    List<User> selectByParams(Map<String, Object> params);

    List<User> selectPage();

    User selectByUsernameAndPassword(Map<String, Object> params);

    int deleteById(Integer id);

    int editNameAndUsername(User user);

    PageResult findPage(PageRequest pageRequest);

}
