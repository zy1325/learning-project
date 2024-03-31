package com.zxh.backsystem.service.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zxh.backsystem.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    //设置过期时间
    private static final long EXPIRE_TIME = 180 * 60 * 1000;

    public String getToken(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getId())) // 将 用户信息 保存到 token 里面
                .withClaim("id", user.getId())
                .withClaim("avatar", user.getAvatar())
                .withClaim("name", user.getName())
                .withClaim("nickname", user.getNickname())
                .withClaim("phone", user.getPhone())
                .withClaim("role", user.getRole())
                .withExpiresAt(date) //一小时后token过期
                .sign(Algorithm.HMAC256("zxh")); // 以 zxh 作为 token 的密钥
        return token;

    }
}
