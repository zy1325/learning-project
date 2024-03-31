package com.zxh.teacher.controller;

import com.zxh.common.common.ResultData;
import com.zxh.teacher.entity.Course;
import com.zxh.teacher.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxh
 * @since 2023-12-22
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    ICourseService courseService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/save")
    public ResultData save(@RequestBody Course course){
        courseService.save(course);
        stringRedisTemplate.delete(course.getTypeId().toString());
        return ResultData.ok();
    }

    @GetMapping("/list/{id}")
    public ResultData getList(@PathVariable Integer id){
        List<Course> list = courseService.getCourseListByUserId(id);
        return ResultData.ok().setData(list);
    }

    @GetMapping("/type/{id}")
    public ResultData getCourseByTypeId(@PathVariable Integer id){
        List<Course> list = courseService.getCourseByTypeId(id);
        return ResultData.ok().setData(list);
    }

    @GetMapping("/{id}")
    public ResultData getCourseById(@PathVariable Integer id){
        Course byId = courseService.getById(id);
        return ResultData.ok().setData(byId);
    }
}
