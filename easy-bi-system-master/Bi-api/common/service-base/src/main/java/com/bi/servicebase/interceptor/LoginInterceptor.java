package com.bi.servicebase.interceptor;

import com.bi.commonutils.result.ResultCode;
import com.bi.servicebase.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Panda
 * @date 2022/8/18 12:12
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String author = request.getHeader("token");
        if (StringUtils.isEmpty(author)){
            throw new GlobalException(ResultCode.TOKEN_ERROR.getCode(),ResultCode.TOKEN_ERROR.getMessage());
        }

        String userId = stringRedisTemplate.opsForValue().get(author);
        if (StringUtils.isEmpty(userId)){
            throw new GlobalException(ResultCode.TOKEN_EXPIRED.getCode(),ResultCode.TOKEN_EXPIRED.getMessage());
        }else {
            return true;
        }
    }
}
