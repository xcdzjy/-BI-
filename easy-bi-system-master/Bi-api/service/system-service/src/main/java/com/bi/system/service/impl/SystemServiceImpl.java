package com.bi.system.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.bi.commonutils.jwt.JwtHelper;
import com.bi.commonutils.jwt.MD5;
import com.bi.commonutils.result.ResultCode;
import com.bi.servicebase.exception.GlobalException;
import com.bi.servicebase.entity.User;
import com.bi.system.service.SystemService;
import com.bi.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Panda
 * @date 2022/8/21 13:09
 */

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private UserService userService;

    private User curUser;

    @Override
    public String login(User login) {
        login.setPassword(MD5.encrypt(login.getPassword()));

        try {
            curUser = userService.login(login);
        }catch (Exception e){
            throw new GlobalException(ResultCode.REPETITION_USERNAME.getCode(), ResultCode.REPETITION_USERNAME.getMessage());
        }

        if (null == curUser){
            throw new GlobalException(ResultCode.LOGIN_ERROR.getCode(),ResultCode.LOGIN_ERROR.getMessage());
        }


        String token = JwtHelper.createToken(curUser.getId(), curUser.getUsername());
        return token;
    }

    @Override
    public void register(User register) {
        String code = register.getCode();

        if(code == null) {
            throw new GlobalException(201,"验证码失效请刷新！");
        }else if (!code.equals(register.getCode())){
            throw new GlobalException(201,"验证码错误！");
        }

        curUser = new User();
        curUser.setPassword(MD5.encrypt(register.getPassword()));
        curUser.setUsername(register.getUsername());
        boolean save = userService.save(curUser);
        if (!save) {
            throw new GlobalException(201, "注册失败!");
        }

    }

    @Override
    public ShearCaptcha getCaptcha(String username) {

        return CaptchaUtil.createShearCaptcha(150, 60, 4, 4);
//        String code = captcha.getCode();

//        stringRedisTemplate.opsForValue().set(REGISTER_CODE_KEY + username, code, REGISTER_CODE_TTL, TimeUnit.MINUTES);

    }

    @Override
    public List<String> getUernames() {
        List<User> list = userService.list(null);
        List<String> result = new ArrayList<>();
        list.forEach(user -> {
            result.add(user.getUsername());
        });
        return result;
    }
}
