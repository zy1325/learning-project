package com.zxh.backsystem.controller;


import com.zxh.backsystem.vo.Form;
import com.zxh.common.common.ExceptionEnum;
import com.zxh.common.common.ResultData;
import com.zxh.common.exception.BizException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class HelloController {

    @GetMapping("/")
    public String Hello() {
        return "hello";
    }

    @PostMapping("/login")
    public Form Login(@RequestBody Form form) {
        form.setToken(UUID.randomUUID().toString());
        return form;
    }

    @GetMapping("/test/{id}")
    public ResultData errorTest(@PathVariable Integer id) {
        if (id == 0) {
            throw new BizException(ExceptionEnum.BODY_NOT_MATCH.getResultCode(), ExceptionEnum.BODY_NOT_MATCH.getResultMsg());
        }
        return ResultData.ok();
    }
}
