package com.zxh.teacher.service;

import com.zxh.teacher.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2023-12-22
 */
public interface ICourseService extends IService<Course> {

    List<Course> getCourseListByUserId( Integer id);

    List<Course> getCourseByTypeId(Integer id);
}
