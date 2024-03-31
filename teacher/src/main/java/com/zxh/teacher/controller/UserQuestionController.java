package com.zxh.teacher.controller;

import com.zxh.common.common.ResultData;
import com.zxh.teacher.service.IUserQuestionService;
import com.zxh.teacher.vo.userQuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxh
 * @since 2024-03-10
 */
@RestController
@RequestMapping("/userQuestion")
public class UserQuestionController {

    @Autowired
    IUserQuestionService userQuestionService;

    @PostMapping("/save")
    public ResultData save(@RequestBody userQuestionVo vo){
        userQuestionService.saveUserQuestion(vo);
        return ResultData.ok();
    }
}
