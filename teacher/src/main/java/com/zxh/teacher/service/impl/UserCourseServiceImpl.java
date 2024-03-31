package com.zxh.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.teacher.entity.Course;
import com.zxh.teacher.entity.UserCourse;
import com.zxh.teacher.mapper.CourseMapper;
import com.zxh.teacher.mapper.UserCourseMapper;
import com.zxh.teacher.service.ICourseService;
import com.zxh.teacher.service.IUserCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxh.teacher.vo.userCourseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2024-01-30
 */
@Service
public class UserCourseServiceImpl extends ServiceImpl<UserCourseMapper, UserCourse> implements IUserCourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public void saveUserCourse(userCourseVo vo) {
        UserCourse userCourse = new UserCourse();
        BeanUtils.copyProperties(vo,userCourse);
        this.save(userCourse);
    }

    @Override
    public List<Course> getCourseById(Integer id) {
        QueryWrapper<UserCourse> user_id = new QueryWrapper<UserCourse>().eq("user_id", id);
        List<UserCourse> list = this.list(user_id);
        List<Integer> collect = list.stream().map(i -> i.getCourseId()).collect(Collectors.toList());
        List<Course> courseList = courseMapper.selectBatchIds(collect);
        return courseList;
    }

    @Override
    public boolean isBuyed(userCourseVo vo) {
        QueryWrapper<UserCourse> eq = new QueryWrapper<UserCourse>().eq("user_id", vo.getUserId()).eq("course_id", vo.getCourseId());
        int count = this.count(eq);
        if(count >0){
            return true;
        }return false;
    }
}
