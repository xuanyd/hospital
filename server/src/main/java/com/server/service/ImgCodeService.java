package com.server.service;

import com.server.entity.ImgCode;

public interface ImgCodeService {
    boolean insert(ImgCode imgCode);

    ImgCode detail(String code, String secret, String loginType);
}
