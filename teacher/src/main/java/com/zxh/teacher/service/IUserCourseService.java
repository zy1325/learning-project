package com.zxh.teacher.service;

import com.zxh.teacher.entity.Course;
import com.zxh.teacher.entity.UserCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxh.teacher.vo.userCourseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2024-01-30
 */
public interface IUserCourseService extends IService<UserCourse> {

    void saveUserCourse(userCourseVo vo);

    List<Course> getCourseById(Integer id);

    boolean isBuyed(userCourseVo vo);
}
