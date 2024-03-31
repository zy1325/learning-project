package com.zxh.teacher.service;

import com.zxh.teacher.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2024-02-15
 */
public interface IExamService extends IService<Exam> {

    List<Exam> getExamInfoByCourseId(Integer id);
}
