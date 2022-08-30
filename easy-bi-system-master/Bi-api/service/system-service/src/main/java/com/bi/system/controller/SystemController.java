package com.bi.system.controller;

import cn.hutool.captcha.ShearCaptcha;
import com.bi.commonutils.result.Result;
import com.bi.servicebase.entity.Captcha;
import com.bi.servicebase.entity.User;
import com.bi.system.service.SystemService;
import com.bi.servicebase.uitls.args.ArgsCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Panda
 * @date 2022/8/16 22:27
 */

@Api(tags = "系统控制器")
@RestController
@CrossOrigin
@Slf4j
public class SystemController{

    @Autowired
    private SystemService systemService;


    @ApiOperation("获取验证码")
    @PostMapping("/user/captcha")
    public Result<Captcha> getCaptcha(@ApiParam("用户名") @RequestBody String username) {
        ShearCaptcha captchaSource =  systemService.getCaptcha(username);
        Captcha captcha = new Captcha();
        captcha.setCode(captchaSource.getCode());
        captcha.setImg(captchaSource.getImageBase64());
        return Result.ok(captcha);
    }


    @ApiOperation("注册")
    @PostMapping("/user/register")
    public Result<Boolean> register(@ApiParam("注册信息") @RequestBody(required = true)User user) {
        systemService.register(user);
        return Result.ok(true);
    }

    @ApiOperation("登录")
    @PostMapping("/user/login")
    public Result<String> login(@ApiParam("登录用户信息") @RequestBody(required = true) User user) {
        String token = systemService.login(user);
        return Result.ok(token);
    }

    @ApiOperation("获取所有用户名")
    @GetMapping("/usernames")
    public Result<List<String>> usernames() {
        List<String> uernames = systemService.getUernames();
        return Result.ok(uernames);
    }

}
