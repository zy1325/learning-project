package com.zxh.teacher.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QuestionDto {
    private Integer id;

    private String text;

    private String isAnswer;

    private Long socore;

    private Integer pid;

    private Integer sort;

    private String title;

    private String type;

    private Long allscore;

    private Integer sum = 0;
}
