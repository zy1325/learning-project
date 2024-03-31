package com.zxh.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.teacher.entity.Exam;
import com.zxh.teacher.mapper.ExamMapper;
import com.zxh.teacher.service.IExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2024-02-15
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements IExamService {

    @Override
    public List<Exam> getExamInfoByCourseId(Integer id) {
        QueryWrapper<Exam> course_id = new QueryWrapper<Exam>().eq("course_id", id);
        List<Exam> list = this.list(course_id);
        return list;
    }
}
