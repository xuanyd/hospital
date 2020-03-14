package com.server.dao;

import com.server.entity.ImgCode;

import java.util.Map;

public interface ImageCodeMapper {

    int insert(ImgCode record);

    ImgCode getByCodeTypeAndSecret(Map<String, Object> params);
}
