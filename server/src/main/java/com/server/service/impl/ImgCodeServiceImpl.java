package com.server.service.impl;

import com.server.dao.ImageCodeMapper;
import com.server.dao.UserMapper;
import com.server.entity.ImgCode;
import com.server.service.ImgCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Transactional
@Service("imgCodeService")
public class ImgCodeServiceImpl implements ImgCodeService {

    @Resource
    private ImageCodeMapper imageCodeMapper;

    @Override
    public boolean insert(ImgCode imgCode) {
        if (imageCodeMapper.insert(imgCode) == 1)
            return true;
        return false;
    }

    @Override
    public ImgCode detail(String code, String secret, String loginType) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("secret", secret);
        params.put("type", loginType);
        return imageCodeMapper.getByCodeTypeAndSecret(params);
    }
}
