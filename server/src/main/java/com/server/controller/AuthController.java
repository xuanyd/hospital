package com.server.controller;

import com.server.entity.ImgCode;
import com.server.entity.Menu;
import com.server.entity.Token;
import com.server.entity.User;
import com.server.service.ImgCodeService;
import com.server.service.MenuService;
import com.server.service.TokenService;
import com.server.service.UserService;
import com.server.util.ImageCodeUtil;
import com.server.util.ImgResult;
import com.server.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @Autowired
    ImgCodeService imgCodeService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Object genToken(@RequestBody Map<String, String> bodyMap) {
        Map<String, Object> resMap = new HashMap<>();
        String loginType = bodyMap.get("type");
        String username = bodyMap.get("username");
        String password = bodyMap.get("password");
        String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());
        try {
            String loginToken = UUID.randomUUID().toString().replace("-", "");
            int userId = 0;
            String code  = bodyMap.get("code");
            String secret = bodyMap.get("secret");
            ImgCode imgCode = imgCodeService.detail(code, secret, loginType);
            if(imgCode == null) {
                resMap.put("code", ResultCode.FAIL);
                resMap.put("message", "登陆失败，请确认验证码是否输入正确");
                return resMap;
            } else {
                User user = userService.getByNameAndPwd(username, md5Str);
                if(user != null)
                    userId = user.getId();
            }
            Token token = new Token();
            token.setUserId(userId);
            token.setType(loginType);
            token.setCreateTime(new Date());
            token.setToken(loginToken);
            token.setStatus(0);
            token.setPhone(username);
            boolean createRes = tokenService.add(token);
            if (createRes){
                resMap.put("code", ResultCode.SUCCESS);
                resMap.put("token", loginToken);
                resMap.put("name", username);
            } else {
                resMap.put("code", ResultCode.FAIL);
                resMap.put("message", "登陆失败，请确认用户名密码是否正确");
            }
        } catch (Exception e) {
            resMap.put("code",ResultCode.EXCEPTION);
            resMap.put("message", "登陆异常");
            e.printStackTrace();
        }
        return resMap;
    }

    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    public Object verifyCode(@RequestBody Map<String, String> bodyMap) {
        Map<String, Object> resMap = new HashMap<>();
        try {
            ImgResult imgResult = ImageCodeUtil.VerifyCode(90, 30, 4);
            String uuid = UUID.randomUUID().toString();
            ImgCode imgCode = new ImgCode();
            imgCode.setCode(imgResult.getCode());
            imgCode.setSecret(uuid);
            imgCode.setCreateTime(new Date());
            imgCode.setType(bodyMap.get("type"));
            boolean insertRes = imgCodeService.insert(imgCode);
            resMap.put("image", imgResult.getImg());
            resMap.put("verifyCode", imgResult.getCode());
            resMap.put("secret",uuid);
            resMap.put("code", ResultCode.SUCCESS);
        } catch (IOException e) {
            resMap.put("code", ResultCode.EXCEPTION);
            e.printStackTrace();
        }
        return resMap;
    }

}
