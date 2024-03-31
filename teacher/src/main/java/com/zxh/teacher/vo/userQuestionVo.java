package com.zxh.teacher.vo;


import lombok.Data;

import java.util.List;

@Data
public class userQuestionVo {
    private Integer userId;
    private List<Integer> questionId;
    private Integer examId;
}
