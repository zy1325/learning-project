package com.zxh.backsystem.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zxh.backsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String token = request.getHeader("token");

        if (token == null) {
            System.out.println("token不存在请重新登录");
            return false;
        }
        String userId = JWT.decode(token).getAudience().get(0);
        if (userId == null) {
            System.out.println("用户不存在");
            return false;
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("zxh")).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            System.out.println("token验证失败");
            return false;
        }
        System.out.println("验证通过！！！");
        return true;
    }
}
