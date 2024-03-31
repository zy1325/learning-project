package com.zxh.teacher.service;

import com.zxh.teacher.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2024-02-23
 */
public interface IQuestionService extends IService<Question> {

    List<List<Question>> getQuetionsById(Integer id);

    void saveQuestionList(List<Question> questionList);

    List<List<Question>> getMultipleQuetionsById(Integer id);

    List<List<Question>> getCreateQuestionsById(Integer id);

    void delQuestionsByExamId(Integer id);

    //获取题库信息
    List<List<Question>> selectQuestionByTypeId(Integer typeId);
}
