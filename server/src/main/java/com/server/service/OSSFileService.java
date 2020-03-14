package com.server.service;

import com.server.entity.OSSFile;
import com.server.entity.OSSFilePath;

import java.util.List;

public interface OSSFileService {
    void syncOssFiles(List<OSSFile> fileList);

    List<OSSFilePath> selectAllTreePaths();

    List<OSSFile> selectByPath(int pathId);
}
