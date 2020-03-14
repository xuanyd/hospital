package com.server.dao;

import com.server.entity.OSSFilePath;

import java.util.List;
import java.util.Map;

public interface OSSFilePathMapper {
    int insertParent(OSSFilePath path);

    OSSFilePath selectByFullPath(String fullPath);

    int insertChild(Map<String, Object> params);

    List<OSSFilePath> selectAll();
}
