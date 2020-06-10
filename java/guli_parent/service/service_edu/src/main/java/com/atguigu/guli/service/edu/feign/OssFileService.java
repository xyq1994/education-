package com.atguigu.guli.service.edu.feign;

import com.atguigu.guli.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-oss")
@Component
public interface OssFileService {

     @GetMapping("/admin/oss/file/test")
     R test();

     @DeleteMapping("/admin/oss/file/remove")
     R removeFile(@RequestBody String url);
}
