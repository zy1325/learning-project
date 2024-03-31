package com.zxh.teacher.controller;

import com.zxh.common.common.ResultData;
import com.zxh.teacher.entity.Exam;
import com.zxh.teacher.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxh
 * @since 2024-02-15
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    IExamService examService;

    @PostMapping("/save")
    public ResultData save(@RequestBody Exam exam){
        examService.save(exam);
        return ResultData.ok();
    }

    @GetMapping("/{id}")
    public ResultData getExamInfoByCourseId(@PathVariable Integer id){
        List<Exam> list =  examService.getExamInfoByCourseId(id);
        return ResultData.ok().setData(list);
    }

    @GetMapping("/exam/{id}")
    public ResultData getExamById(@PathVariable Integer id){
        Exam byId = examService.getById(id);
        return ResultData.ok().setData(byId);
    }
}
