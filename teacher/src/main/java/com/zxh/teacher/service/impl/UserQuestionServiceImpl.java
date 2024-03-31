package com.zxh.teacher.service.impl;

import com.zxh.teacher.entity.UserQuestion;
import com.zxh.teacher.mapper.UserQuestionMapper;
import com.zxh.teacher.service.IUserQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxh.teacher.vo.userQuestionVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2024-03-10
 */
@Service
public class UserQuestionServiceImpl extends ServiceImpl<UserQuestionMapper, UserQuestion> implements IUserQuestionService {

    @Override
    public void saveUserQuestion(userQuestionVo vo) {
        List<UserQuestion> list = new ArrayList<>();
        for (Integer integer : vo.getQuestionId()) {
            UserQuestion userQuestion = new UserQuestion();
            userQuestion.setExamId(vo.getExamId());
            userQuestion.setUserId(vo.getUserId());
            userQuestion.setQuestionId(integer);
            list.add(userQuestion);
        }
        this.saveBatch(list);
    }
}
