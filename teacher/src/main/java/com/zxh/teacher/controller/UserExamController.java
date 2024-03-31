package com.zxh.teacher.controller;

import com.zxh.common.common.ResultData;
import com.zxh.teacher.dto.HighestDto;
import com.zxh.teacher.dto.QuestionDto;
import com.zxh.teacher.entity.UserExam;
import com.zxh.teacher.service.IUserExamService;
import com.zxh.teacher.vo.userExamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxh
 * @since 2024-02-28
 */
@RestController
@RequestMapping("/userExam")
public class UserExamController {

    @Autowired
    IUserExamService userExamService;

    @PostMapping("/save")
    public ResultData save (@RequestBody UserExam userExam){
        userExamService.save(userExam);
        return ResultData.ok();
    }

    @GetMapping("/grade/{id}")
    public ResultData getGrade(@PathVariable Integer id){
        List<Integer> list = userExamService.getGrade(id);
        return ResultData.ok().setData(list);
    }

    @PostMapping("/grade/score")
    public ResultData getUserExamScore(@RequestBody userExamVo user){
        Integer score = userExamService.getUserExamScore(user);
        return ResultData.ok().setData(score);
    }

    @GetMapping("/statistics/average/{id}")
    public ResultData getUserExamAverage(@PathVariable Integer id){
        double averScore = userExamService.getUserExamAverage(id);
        return ResultData.ok().setData(averScore);
    }

    @GetMapping("/statistics/highest/{id}")
    public ResultData getUserExamHighest(@PathVariable Integer id){
        HighestDto one = userExamService.getUserExamHighest(id);
        return ResultData.ok().setData(one);
    }

    @GetMapping("/statistics/questions/{id}")
    public ResultData getExamStatisticsByExamId(@PathVariable Integer id){
        List< List<QuestionDto>> examStatisticsByExamId = userExamService.getExamStatisticsByExamId(id);
        return ResultData.ok().setData(examStatisticsByExamId);
    }
}
