package com.zxh.teacher.controller;

import com.zxh.common.common.ExceptionEnum;
import com.zxh.common.common.ResultData;
import com.zxh.teacher.entity.Course;
import com.zxh.teacher.service.IUserCourseService;
import com.zxh.teacher.vo.userCourseVo;
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
 * @since 2024-01-30
 */
@RestController
@RequestMapping("/userCourse")
public class UserCourseController {

    @Autowired
    IUserCourseService userCourseService;

    @PostMapping("/save")
    public ResultData saveUserCourse(@RequestBody userCourseVo vo){
        userCourseService.saveUserCourse(vo);
        return ResultData.ok();
    }

    @GetMapping("/{id}")
    public ResultData getCourseById(@PathVariable Integer id){
        List<Course> list = userCourseService.getCourseById(id);
        return ResultData.ok().setData(list);
    }

    @PostMapping("/find")
    public ResultData isBuyed(@RequestBody userCourseVo vo){
        if(userCourseService.isBuyed(vo)){
            return ResultData.ok();
        }else {
            return ResultData.error(1,"该课程暂未购买，请购买");        }
    }
}
