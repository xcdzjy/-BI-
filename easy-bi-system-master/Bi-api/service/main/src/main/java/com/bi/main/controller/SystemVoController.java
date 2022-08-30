package com.bi.main.controller;

import cn.hutool.captcha.ShearCaptcha;
import com.bi.commonutils.jwt.JwtHelper;
import com.bi.commonutils.result.Result;
import com.bi.main.entity.vo.LoginVo;
import com.bi.main.entity.vo.RegisterVo;
import com.bi.main.service.feign.SystemFeignService;
import com.bi.main.util.CheckError;
import com.bi.servicebase.controller.BaseController;
import com.bi.servicebase.entity.Captcha;
import com.bi.servicebase.entity.DataSource;
import com.bi.servicebase.entity.User;
import com.bi.servicebase.exception.GlobalException;
import com.bi.servicebase.uitls.args.ArgsCheck;
import com.bi.servicebase.uitls.json.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import static com.bi.main.constance.RedisConstants.REGISTER_CODE_KEY;
import static com.bi.main.constance.RedisConstants.REGISTER_CODE_TTL;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Panda
 * @date 2022/8/25 12:42
 */
@Api(tags = "系统控制器")
@RestController
@RequestMapping("/api/v1/sys")
@CrossOrigin
@Slf4j
public class SystemVoController extends BaseController{


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private SystemFeignService systemFeignService;

    private User user;

    List<String> usernames;

    @ApiOperation("获取验证码")
    @GetMapping("/user/captcha")
    public Result<?> getCaptcha(@ApiParam("用户名") @RequestParam("username") String username) {
        ArgsCheck.check(username);
        log.info("{} 试图创建用户 正在获取验证码...",username);
        Result<Captcha> result = systemFeignService.getCaptcha(username);
        CheckError.check(result);
        Captcha captcha = result.getData();
        String code = captcha.getCode();
        stringRedisTemplate.opsForValue().set(REGISTER_CODE_KEY + username, code, REGISTER_CODE_TTL, TimeUnit.MINUTES);
        return Result.ok(captcha.getImg());
    }


    @ApiOperation("注册")
    @PostMapping("/user/register")
    public Result<?> register(@ApiParam("注册信息") @RequestBody(required = true) RegisterVo register) {
        String usernamesStr = stringRedisTemplate.opsForValue().get("username:all");
        if (StringUtils.isEmpty(usernamesStr)){
            Result<List<String>> result = systemFeignService.usernames();
            CheckError.check(result);
            this.usernames = result.getData();
        }else {
            usernames = jsonUtil.read(usernamesStr,String.class);
        }

        if (usernames.contains(register.getUsername())){
            throw new GlobalException(201,"用户名重复");
        }

        String code = stringRedisTemplate.opsForValue().get(REGISTER_CODE_KEY + register.getUsername());

        user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setCode(code);
        Result<Boolean> result = systemFeignService.register(user);
        CheckError.check(result);
        log.info("{} 成功注册...",register.getUsername());
        return result;
    }

    @ApiOperation("登录")
    @PostMapping("/user/login")
    public Result<?> login(@ApiParam("登录用户信息") @RequestBody(required = true) LoginVo loginVo) {
        user = new User();
        user.setUsername(loginVo.getUsername());
        user.setPassword(loginVo.getPassword());
        Result<String> result = systemFeignService.login(user);
        CheckError.check(result);
        String token = result.getData();

        log.info("{} 登录了...",loginVo.getUsername());
        stringRedisTemplate.opsForValue().set(token,"login",7, TimeUnit.DAYS);
        return result;
    }

    @ApiOperation("登出")
    @PostMapping("/user/logout")
    public Result<?> logout() {
        String token = request.getHeader("token");
        String name = JwtHelper.getNickName(token);
        stringRedisTemplate.delete(token);
        log.info("{} 登出...",name);
        return Result.ok(true);
    }
}
