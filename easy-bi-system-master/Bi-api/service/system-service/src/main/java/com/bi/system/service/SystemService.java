package com.bi.system.service;

import cn.hutool.captcha.ShearCaptcha;
import com.bi.servicebase.entity.User;

import java.util.List;

/**
 * @author Panda
 * @data 2022/8/21 13:08
 */
public interface SystemService {
    String login(User user);

    void register(User user);

    ShearCaptcha getCaptcha(String username);

    List<String> getUernames();
}
