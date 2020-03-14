package com.server.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.server.entity.OSSFile;
import com.server.entity.OSSFilePath;
import com.server.service.OSSFileService;
import com.server.util.AliOSSUtil;
import com.server.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oss-file")
public class FileStoreController {

    @Autowired
    private Environment env;

    @Autowired
    private OSSFileService fileService;

    @RequestMapping(value = "/syncOSSFiles", method = RequestMethod.GET)
    public Map syncOssFiles() {
        Map<String, Object> retMap = new HashMap<>();
        String endpoint = env.getProperty("oss.endpoint");
        String accessKeyId = env.getProperty("oss.accessKeyId");
        String accessKeySecret = env.getProperty("oss.accessKeySecret");
        String bucketName = env.getProperty("oss.bucketName");
        AliOSSUtil fileUtil = new AliOSSUtil();
        List<OSSFile> fileList = fileUtil.listFiles(endpoint, accessKeyId, accessKeySecret, bucketName, "");
        fileService.syncOssFiles(fileList);
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("message", "发现云文件:" + fileList.size());
        return retMap;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map listFiles(@RequestBody Map<String, String> body) {
        Map<String, Object> retMap = new HashMap<>();
        int pathId = Integer.valueOf(body.get("path"));
        List<OSSFile> fileList = this.fileService.selectByPath(pathId);
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("dataList", fileList);
        return retMap;
    }

    @RequestMapping(value = "/path-list", method = RequestMethod.GET)
    public Map listPaths() {
        Map<String, Object> retMap = new HashMap<>();
        List<OSSFilePath> treeList = new ArrayList<>();
        List<OSSFilePath> pathList = fileService.selectAllTreePaths();
        for (int i = 0; i < pathList.size(); i++) {
            OSSFilePath tmpPath = pathList.get(i);
            if(tmpPath.getpId() == 0) {
                tmpPath.setChilds(filterChilds(tmpPath, pathList));
                treeList.add(tmpPath);
            }
        }
        retMap.put("code", ResultCode.SUCCESS);
        retMap.put("dataList", treeList);
        return retMap;
    }

    private List<OSSFilePath> filterChilds(OSSFilePath rootPath, List<OSSFilePath> otherPath) {
        List<OSSFilePath> childs = new ArrayList<>();
        for(int i = 0; i < otherPath.size(); i++) {
            OSSFilePath tmp = otherPath.get(i);
            if(tmp.getpId() == rootPath.getId()) {
                tmp.setChilds(filterChilds(tmp, otherPath));
                childs.add(tmp);
            }
        }
        return childs;
   }

}
