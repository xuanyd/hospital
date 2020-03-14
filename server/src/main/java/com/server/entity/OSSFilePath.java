package com.server.entity;

import java.util.List;

public class OSSFilePath {

    private Integer id;
    private Integer pId;
    private String path;
    private String fullPath;
    private List<OSSFilePath> childs;

    public List<OSSFilePath> getChilds() {
        return childs;
    }

    public void setChilds(List<OSSFilePath> childs) {
        this.childs = childs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }


}
