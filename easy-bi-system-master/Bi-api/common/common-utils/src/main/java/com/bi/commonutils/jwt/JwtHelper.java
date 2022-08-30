package com.bi.commonutils.jwt;

/**
 * @author Panda
 * @date 2022/5/5 16:36
 */

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtHelper {
    private static long tokenExpiration = 14*24*60*60*1000;
    private static String tokenSignKey = "#{easy-bi-system}#";

    //生成token字符串
    public static String createToken(String userId, String nickName) {
        String token = Jwts.builder()

                .setSubject("YYGH-USER")

                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))

                .claim("userId", userId)
//                .claim("userName", userName)
                .claim("nickName", nickName)

                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从token字符串获取userid
    public static String getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String userId = (String)claims.get("userId");
        return userId;
    }

    //从token字符串获取nickName
    public static String getNickName(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)(claims.get("nickName"));
    }

//    //从token字符串获取userName
//    public static String getUserName(String token) {
//        if(StringUtils.isEmpty(token)) return "";
//        Jws<Claims> claimsJws
//                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
//        Claims claims = claimsJws.getBody();
//        return (String)claims.get("userName");
//    }

    //判断token是否有效
    public static boolean isExpiration(String token){
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }


    /**
     * 刷新Token
     * @param token
     * @return
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = JwtHelper.createToken(getUserId(token), getNickName(token));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

}
