package com.zxh.teacher.controller;

import com.zxh.common.common.ResultData;
import com.zxh.teacher.entity.Question;
import com.zxh.teacher.service.IQuestionService;
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
 * @since 2024-02-23
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    IQuestionService questionService;

    @PostMapping("/save")
    public ResultData save(@RequestBody List<Question> questionList){
        questionService.saveQuestionList(questionList);
        return ResultData.ok();
    }

    @GetMapping("/{id}")
    public ResultData getQuetionsById(@PathVariable Integer id){
        List<List<Question>> questionList = questionService.getQuetionsById(id);
        return ResultData.ok().setData(questionList);
    }

    @GetMapping("/Multiple/{id}")
    public ResultData getMultipleQuetionsById(@PathVariable Integer id){
        List<List<Question>> questionList = questionService.getMultipleQuetionsById(id);
        return ResultData.ok().setData(questionList);
    }

    @GetMapping("/question/{id}")
    public ResultData getCreateQuestionsById(@PathVariable Integer id){
        List<List<Question>> questionList = questionService.getCreateQuestionsById(id);
        return ResultData.ok().setData(questionList);
    }

    @GetMapping("/delQuestions/{id}")
    public ResultData delQuestionsByExamId(@PathVariable Integer id){
        questionService.delQuestionsByExamId(id);
        return ResultData.ok();
    }

    @GetMapping("/selectQuestion/{typeId}")
    public ResultData selectQuestionByTypeId(@PathVariable Integer typeId){
        List<List<Question>> questionList = questionService.selectQuestionByTypeId(typeId);
        return ResultData.ok().setData(questionList);
    }

}
