package com.atguigu.guli.service.edu.controller;

import com.atguigu.guli.common.base.result.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {



    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("name","admin").data("roles","[admin]").data("avatar","http://pic1.zhimg.com/50/v2-fbe356c74816b9b3cfcf80ff1758fbd4_hd.jpg");
    }
}
