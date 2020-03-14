package com.server.service.impl;

import com.server.dao.OSSFileMapper;
import com.server.dao.OSSFilePathMapper;
import com.server.entity.OSSFile;
import com.server.entity.OSSFilePath;
import com.server.service.OSSFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("fileService")
public class OSSFileServiceImpl implements OSSFileService {

    @Resource
    private OSSFilePathMapper ossFilePathMapper;

    @Resource
    private OSSFileMapper ossFileMapper;

    @Override
    public void syncOssFiles(List<OSSFile> fileList) {
        List<OSSFilePath> pathList = new ArrayList<>();
        String[] dirs;
        for (OSSFile ossFile: fileList) {
            String filePath = ossFile.getPath();
            if(filePath.equals("") || filePath == null)
                continue;
            if(filePath.split("/").length > 1){
                String[] splitDirs = filePath.split("/");
                dirs = new String[splitDirs.length];
                int idx = 0;
                for(String d : splitDirs) {
                    if(idx == 0)
                        dirs[idx] = d + "/";
                    else
                        dirs[idx] = dirs[idx - 1] + d + "/";
                    idx ++;
                }
            } else {
                dirs = new String[]{ filePath };
            }
            insertPaths(dirs);
            insertFile(ossFile);
        }
    }

    @Override
    public List<OSSFilePath> selectAllTreePaths() {
        List<OSSFilePath> allPath = this.ossFilePathMapper.selectAll();
        return allPath;
    }

    @Override
    public List<OSSFile> selectByPath(int pathId) {
        return  this.ossFileMapper.selectByPath(pathId);
    }

    private void insertFile(OSSFile ossFile) {
        int res = this.ossFileMapper.exists(ossFile);
        if(res == 0 )
            this.ossFileMapper.insert(ossFile);
    }

    private void insertPaths(String[] paths){
        for (String path : paths) {
            OSSFilePath pathEntity = new OSSFilePath();
            pathEntity.setFullPath(path);
            OSSFilePath exist = this.ossFilePathMapper.selectByFullPath(path);
            if(exist != null){
                System.out.println("path is exists:" + path);
                continue;
            }
            if(path.split("/").length == 1) {
                pathEntity.setPath(path.split("/")[0]);
                this.ossFilePathMapper.insertParent(pathEntity);
            } else {
                if(path.split("/").length == 2){
                    Map<String, Object> params = new HashMap<>();
                    params.put("parentPath", path.split("/")[0] + "/");
                    params.put("path", path.split("/")[1]);
                    params.put("fullPath", pathEntity.getFullPath());
                    this.ossFilePathMapper.insertChild(params);
                }
                if(path.split("/").length > 2){
                    Map<String, Object> params = new HashMap<>();
                    String[] pathArray = path.split("/");
                    params.put("parentPath", path.substring(0, path.lastIndexOf(pathArray[pathArray.length-1])));
                    params.put("path", pathArray[pathArray.length-1]);
                    params.put("fullPath", path);
                    this.ossFilePathMapper.insertChild(params);
                }
            }
        }
    }

}
