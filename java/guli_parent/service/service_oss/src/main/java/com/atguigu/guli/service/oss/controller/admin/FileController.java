package com.atguigu.guli.service.oss.controller.admin;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.result.ResultCodeEnum;
import com.atguigu.guli.common.base.util.ExceptionUtils;
import com.atguigu.guli.service.base.exception.CustomException;
import com.atguigu.guli.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@Api(tags = "阿里云文件管理")
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {


    @Autowired
    private FileService fileService;


    @PostMapping("upload")
    @ApiOperation("文件上传")
    public R upload(
            @ApiParam(value = "上传的文件",required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(value = "保存位置",required = true)
            @RequestParam("module") String module) {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);
            return R.ok().message("文件上传成功").data("url",uploadUrl);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new CustomException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation(value = "文件删除")
    @DeleteMapping("remove")
    public R remove(@RequestBody @ApiParam(value = "要删除的文件url路径",required = true) String url){
        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }


    @ApiOperation(value = "测试")
    @GetMapping("test")
    public R test() {
        log.info("service_oss中test方法被调用");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
}
