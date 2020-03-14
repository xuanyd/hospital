package com.server.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.server.entity.OSSFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AliOSSUtil {

    public void simpleUpload(String endpoint, String accessKey, String secret, String filePath) {
        OSSClient ossClient = new OSSClient(endpoint, accessKey, secret);
        ossClient.putObject("<yourBucketName>", "<yourObjectName>", new File(filePath));
        ossClient.shutdown();
    }


    /**
     * 列举文件。
     * 如果不设置KeyPrefix，则列举存储空间下所有的文件。KeyPrefix，则列举包含指定前缀的文件。
     * @param endpoint
     * @param accessKey
     * @param secret
     * @return
     */
    public List<OSSFile> listFiles(String endpoint, String accessKey, String secret, String bucketName, String keyPrefix) {
        List<OSSFile> fileList = new ArrayList<>();
        OSSClient ossClient = new OSSClient(endpoint, accessKey, secret);
        ObjectListing objectListing = ossClient.listObjects(bucketName, keyPrefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            if( !s.getKey().endsWith("/")) {
                OSSFile ossFile = new OSSFile();
                String name = s.getKey();
                if(name.indexOf('/') > 0) {
                    ossFile.setName(name.substring(name.lastIndexOf('/') + 1, name.length()));
                    ossFile.setPath(name.substring(0, name.lastIndexOf('/') + 1));
                    ossFile.setSize(s.getSize());
                } else {
                    ossFile.setName(name);
                    ossFile.setPath("");
                }
                fileList.add(ossFile);
            }
            System.out.println("\t" + s.getKey() + ", size"  + s.getSize());
        }
        return  fileList;
    }

    public static void main(String[] args) {
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        String key = "LTAIHCf3hVCA6Q2t";
        String secrect = "s1bvMaXympvvarrcIZtrm61yV6HZKc";
        String bucket = "vedio-test1";
        AliOSSUtil util = new AliOSSUtil();
        util.listFiles(endpoint, key, secrect, bucket, null);
    }



}
