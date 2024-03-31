package com.zxh.teacher.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.common.common.ResultData;
import com.zxh.teacher.Feign.SystemFeignService;
import com.zxh.teacher.dto.HighestDto;
import com.zxh.teacher.dto.QuestionDto;
import com.zxh.teacher.entity.Question;
import com.zxh.teacher.entity.UserExam;
import com.zxh.teacher.entity.UserQuestion;
import com.zxh.teacher.mapper.UserExamMapper;
import com.zxh.teacher.mapper.UserQuestionMapper;
import com.zxh.teacher.service.IQuestionService;
import com.zxh.teacher.service.IUserExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxh.teacher.service.IUserQuestionService;
import com.zxh.teacher.vo.userExamVo;
import com.zxh.teacher.vo.userInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2024-02-28
 */
@Service
public class UserExamServiceImpl extends ServiceImpl<UserExamMapper, UserExam> implements IUserExamService {

    public static ExecutorService service = Executors.newFixedThreadPool(10);

    @Autowired
    SystemFeignService systemFeignService;

    @Autowired
    IQuestionService questionService;

    @Autowired
    IUserQuestionService userQuestionService;

    @Autowired
    UserQuestionMapper userQuestionMapper;

    @Override
    public List<Integer> getGrade(Integer id) {
        QueryWrapper<UserExam> user_id = new QueryWrapper<UserExam>().eq("user_id", id);
        List<UserExam> list = this.list(user_id);
        List<Integer> collect = list.stream().map(item -> item.getExamId()).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Integer getUserExamScore(userExamVo user) {
        QueryWrapper<UserExam> eq = new QueryWrapper<UserExam>().eq("user_id", user.getUserId()).eq("exam_id", user.getExamId());
        UserExam one = this.getOne(eq);
        return one.getScore();
    }

    @Override
    public double getUserExamAverage(Integer id) {
        QueryWrapper<UserExam> exam_id = new QueryWrapper<UserExam>().eq("exam_id", id);
        List<UserExam> list = this.list(exam_id);
        double average = list.stream().mapToInt(i -> i.getScore()).average().getAsDouble();
        return average;
    }

    @Override
    public HighestDto getUserExamHighest(Integer id) {
        QueryWrapper<UserExam> exam_id = new QueryWrapper<UserExam>().eq("exam_id", id).orderByDesc("score");
        List<UserExam> list = this.list(exam_id);
        UserExam userExam = list.get(0);
        ResultData userInfoById = systemFeignService.getUserInfoById(userExam.getUserId());
        userInfoVo userInfo = userInfoById.getData(new TypeReference<userInfoVo>() {
        });
        HighestDto highestDto = new HighestDto();
        highestDto.setScore(userExam.getScore());
        highestDto.setNickName(userInfo.getNickname());
        highestDto.setUrl(userInfo.getAvatar());
        return highestDto;
    }

    @Override
    public  List< List<QuestionDto>> getExamStatisticsByExamId(Integer id) {
        //1.获取所有的题目项
        QueryWrapper<Question> pid = new QueryWrapper<Question>().eq("pid", id);
        List<Question> list = questionService.list(pid);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        //2.弄成一个以id为索引的map集合
        for (Question question : list) {
            QuestionDto questionDto = new QuestionDto();
            CompletableFuture<Object> futureCopyProperties = CompletableFuture.supplyAsync(() -> {
                BeanUtils.copyProperties(question,questionDto);
                return null;
            }, service);
            CompletableFuture<Object> futureSum = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<UserQuestion> question_id = new QueryWrapper<UserQuestion>().eq("question_id", question.getId());
                Integer integer = userQuestionMapper.selectCount(question_id);
                questionDto.setSum(integer);
                return null;
            }, service);
            CompletableFuture<Void> allOf = CompletableFuture.allOf(futureCopyProperties, futureSum);
            try {
                allOf.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            questionDtoList.add(questionDto);
        }
        Map<Integer, List<QuestionDto>> collect = questionDtoList.stream().collect(Collectors.groupingBy(i -> i.getSort()));
        List<List<QuestionDto>> result = new ArrayList<>();
        for (List<QuestionDto> value : collect.values()) {
            result.add(value);
        }
        return result;
    }
}
