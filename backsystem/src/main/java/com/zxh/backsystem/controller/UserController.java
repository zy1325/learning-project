package com.zxh.backsystem.controller;

import com.zxh.backsystem.entity.User;
import com.zxh.backsystem.service.IUserService;
import com.zxh.backsystem.vo.LoginUser;
import com.zxh.common.common.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxh
 * @since 2023-12-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ResultData login(@RequestBody LoginUser loginUser) {
        String token = userService.login(loginUser);
        if (token != null) {
            return ResultData.ok().put("token", token);
        }
        return ResultData.ok();
    }

    @GetMapping("/{id}")
    public ResultData getUserInfoById(@PathVariable Integer id){
        User byId = userService.getById(id);
        return ResultData.ok().setData(byId);
    }

    @PostMapping("/register")
    public ResultData register(@RequestBody User user){
        user.setRole(1);
        userService.save(user);
        return ResultData.ok();
    }

    @GetMapping("/isRegistered/{name}")
    public ResultData isNameRegistered (@PathVariable String name){
        Boolean k = userService.isNameRegistered(name);
        return ResultData.ok().setData(k);
    }

    @GetMapping("/isRegistered/phone/{phone}")
    public ResultData isPhoneRegistered (@PathVariable String phone){
        Boolean k = userService.isPhoneRegistered(phone);
        return ResultData.ok().setData(k);
    }
}
