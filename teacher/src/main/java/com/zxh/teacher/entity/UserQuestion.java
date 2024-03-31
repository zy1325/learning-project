package com.zxh.teacher.entity;

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
 * @since 2024-03-10
 */
@TableName("sys_user_question")
@ApiModel(value = "UserQuestion对象", description = "")
@Data
@ToString
public class UserQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer questionId;

    private Integer examId;


}
