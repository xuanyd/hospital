package com.server.dao;

import com.server.entity.OSSFile;

import java.util.List;

public interface OSSFileMapper {
    int insert(OSSFile file);

    List<OSSFile> selectByPath(int pathId);

    int exists(OSSFile ossFile);
}
