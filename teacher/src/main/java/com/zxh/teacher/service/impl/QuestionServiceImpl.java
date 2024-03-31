package com.zxh.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.teacher.entity.Exam;
import com.zxh.teacher.entity.Question;
import com.zxh.teacher.mapper.QuestionMapper;
import com.zxh.teacher.service.IExamService;
import com.zxh.teacher.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2024-02-23
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    IExamService examService;

    @Override
    public List<List<Question>> getQuetionsById(Integer id) {
        QueryWrapper<Question> pid = new QueryWrapper<Question>().eq("pid", id).eq("type","s");
        List<Question> list = this.list(pid);
        Map<Integer, List<Question>> collect = list.stream().collect(Collectors.groupingBy(item -> item.getSort()));
        List<List<Question>> quetionsList = new ArrayList<List<Question>>();
        collect.forEach((key,value) ->{
            quetionsList.add(value);
        });
        return quetionsList;
    }

    @Override
    public void saveQuestionList(List<Question> questionList) {
        Question question = questionList.get(0);
        QueryWrapper<Question> pid = new QueryWrapper<Question>().eq("pid", question.getPid());
        this.remove(pid);
        this.saveBatch(questionList);
    }

    @Override
    public List<List<Question>> getMultipleQuetionsById(Integer id) {
        QueryWrapper<Question> pid = new QueryWrapper<Question>().eq("pid", id).eq("type","d");
        List<Question> list = this.list(pid);
        Map<Integer, List<Question>> collect = list.stream().collect(Collectors.groupingBy(item -> item.getSort()));
        List<List<Question>> quetionsList = new ArrayList<List<Question>>();
        collect.forEach((key,value) ->{
            quetionsList.add(value);
        });
        return quetionsList;
    }

    @Override
    public List<List<Question>> getCreateQuestionsById(Integer id) {
        QueryWrapper<Question> pid = new QueryWrapper<Question>().eq("pid", id);
        List<Question> list = this.list(pid);
        Map<Integer, List<Question>> collect = list.stream().collect(Collectors.groupingBy(item -> item.getSort()));
        List<List<Question>> quetionsList = new ArrayList<List<Question>>();
        collect.forEach((key,value) ->{
            quetionsList.add(value);
        });
        return quetionsList;
    }

    @Override
    public void delQuestionsByExamId(Integer id) {
        QueryWrapper<Question> delQuestionsWrapper = new QueryWrapper<Question>().eq("pid", id);
        this.remove(delQuestionsWrapper);
    }

    @Override
    public List<List<Question>> selectQuestionByTypeId(Integer typeId) {
        QueryWrapper<Exam> examByTypeQueryWrapper = new QueryWrapper<Exam>().eq("type_id", typeId);
        List<Exam> list = examService.list(examByTypeQueryWrapper);
        List<List<Question>> quetionsList = new ArrayList<List<Question>>();
        for (Exam exam : list) {
            QueryWrapper<Question> questionByExamId = new QueryWrapper<Question>().eq("pid", exam.getId());
            List<Question> questionList = this.list(questionByExamId);
            Map<Integer, List<Question>> collect = questionList.stream().collect(Collectors.groupingBy(item -> item.getSort()));
            collect.forEach((key,value) ->{
                quetionsList.add(value);
            });
        }
        return quetionsList;
    }
}
