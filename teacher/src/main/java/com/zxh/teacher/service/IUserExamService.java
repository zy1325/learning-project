package com.zxh.teacher.service;

import com.zxh.teacher.dto.HighestDto;
import com.zxh.teacher.dto.QuestionDto;
import com.zxh.teacher.entity.UserExam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxh.teacher.vo.userExamVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2024-02-28
 */
public interface IUserExamService extends IService<UserExam> {

    List<Integer> getGrade(Integer id);

    Integer getUserExamScore(userExamVo user);

    double getUserExamAverage(Integer id);


    HighestDto getUserExamHighest(Integer id);

    List< List<QuestionDto>> getExamStatisticsByExamId(Integer id);
}
