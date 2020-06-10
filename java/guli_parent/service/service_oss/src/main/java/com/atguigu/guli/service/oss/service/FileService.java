package com.atguigu.guli.service.oss.service;

import java.io.InputStream;

public interface FileService {

    /**
     * 阿里云文件上传
     * @param inputStream           //文件上传流
     * @param module                //文件夹名称
     * @param originalFilename      //文件名称
     * @return 返回文件在oss上面的地址
     */
    String upload(InputStream inputStream, String module, String originalFilename);
}
