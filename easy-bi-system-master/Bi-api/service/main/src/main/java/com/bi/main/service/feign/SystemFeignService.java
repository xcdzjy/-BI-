package com.bi.main.service.feign;

import com.bi.commonutils.result.Result;
import com.bi.servicebase.entity.Captcha;
import com.bi.servicebase.entity.User;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Panda
 * @data 2022/8/27 14:30
 */
@Component
@FeignClient(name = "system-service")
public interface SystemFeignService {

    @GetMapping("/usernames")
    public Result<List<String>> usernames();

    @PostMapping("/user/login")
    public Result<String> login(@ApiParam("登录用户信息") @RequestBody(required = true) User user);

    @PostMapping("/user/captcha")
    public Result<Captcha> getCaptcha(@ApiParam("用户名") @RequestBody String username);

    @PostMapping("/user/register")
    public Result<Boolean> register(@ApiParam("注册信息") @RequestBody(required = true)User user);


}
