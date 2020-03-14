package com.server.entity;

import java.util.Date;

public class ImgCode {
    private Integer id;
    private String secret;
    private String code;
    private String type;
    private Date createTime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}