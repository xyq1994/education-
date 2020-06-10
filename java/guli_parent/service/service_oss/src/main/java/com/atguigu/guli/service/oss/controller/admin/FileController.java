package com.atguigu.guli.service.oss.controller.admin;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@CrossOrigin
@RestController
@Api(tags = "阿里云文件管理")
@RequestMapping("/admin/oss/file")
public class FileController {


    @Autowired
    private FileService fileService;


    @PostMapping("upload")
    @ApiOperation("文件上传")
    public R upload(
            @ApiParam(value = "上传的文件",required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(value = "保存位置",required = true)
            @RequestParam("module") String module) throws IOException {

        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String uploadUrl = fileService.upload(inputStream, module, originalFilename);
        return R.ok().message("文件上传成功").data("url",uploadUrl);
    }
}
