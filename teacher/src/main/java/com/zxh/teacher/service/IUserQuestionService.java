package com.zxh.teacher.service;

import com.zxh.teacher.entity.UserQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxh.teacher.vo.userQuestionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2024-03-10
 */
public interface IUserQuestionService extends IService<UserQuestion> {

    void saveUserQuestion(userQuestionVo vo);
}
