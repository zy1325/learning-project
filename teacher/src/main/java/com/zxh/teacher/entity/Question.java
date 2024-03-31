package com.zxh.teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxh
 * @since 2024-02-23
 */
@TableName("sys_question")
@ApiModel(value = "Question对象", description = "")
@Data
@ToString
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String text;

    private String isAnswer;

    private Long socore;

    private Integer pid;

    private Integer sort;

    private String title;

    private String type;

    private Long allscore;

}
