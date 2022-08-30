package com.bi.commonutils.jwt;

/**
 * @author Panda
 * @date 2022/5/5 16:37
 */

import javax.servlet.http.HttpServletRequest;

public class AuthContextHolder {

    //从请求头token获取userid
    public static String getUserIdToken(HttpServletRequest request) {
        //从请求头token
        String token = request.getHeader("token");
        //调用工具类
        String userId = JwtHelper.getUserId(token);
        return userId;
    }

    //从请求头token获取nickName
    public static String getUserName(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取username
        String nickName = JwtHelper.getNickName(token);
        return nickName;
    }
}
