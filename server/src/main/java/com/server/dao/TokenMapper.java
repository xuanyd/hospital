package com.server.dao;

import com.server.entity.Token;

import java.util.List;
import java.util.Map;

public interface TokenMapper {

    Token selectById(Integer id);

    List<Token> selectByParams(Map<String, Object> params);

    int insert(Token token);
}
