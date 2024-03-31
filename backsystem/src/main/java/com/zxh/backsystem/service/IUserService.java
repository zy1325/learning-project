package com.zxh.backsystem.service;

import com.zxh.backsystem.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxh.backsystem.vo.LoginUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxh
 * @since 2023-12-03
 */
public interface IUserService extends IService<User> {

    String login(LoginUser loginUser);

    Boolean isNameRegistered(String name);

    Boolean isPhoneRegistered(String phone);
}
