package com.server.service.impl;

import com.server.dao.TokenMapper;
import com.server.entity.Token;
import com.server.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("tokenService")
@Transactional
public class TokenServiceImpl implements TokenService{


    @Resource
    private TokenMapper tokenMapper;


    @Override
    public boolean add(Token token) {
        if(tokenMapper.insert(token) == 1)
            return true;
        return false;
    }
}
