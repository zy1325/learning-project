package com.zxh.backsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.backsystem.entity.User;
import com.zxh.backsystem.mapper.UserMapper;
import com.zxh.backsystem.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxh.backsystem.service.jwt.TokenService;
import com.zxh.backsystem.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxh
 * @since 2023-12-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public String login(LoginUser loginUser) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("name", loginUser.getUsername()).eq("password", loginUser.getPassword());
        User user = userMapper.selectOne(userQueryWrapper);
        String token = tokenService.getToken(user);
        if (token != null) {
            return token;
        }
        return null;
    }

    @Override
    public Boolean isNameRegistered(String name) {
        QueryWrapper<User> nameQueryWrapper = new QueryWrapper<User>().eq("name", name);
        User one = this.getOne(nameQueryWrapper);
        if(one!=null){
            return true;
        }
        return false;
    }

    @Override
    public Boolean isPhoneRegistered(String phone) {
        QueryWrapper<User> nameQueryWrapper = new QueryWrapper<User>().eq("phone", phone);
        User one = this.getOne(nameQueryWrapper);
        if(one!=null){
            return true;
        }
        return false;
    }
}
