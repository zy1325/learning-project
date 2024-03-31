package com.zxh.teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @since 2023-12-22
 */
@TableName("sys_course")
@ApiModel(value = "Course对象", description = "")
@Data
@ToString
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer teacherId;

    private Integer typeId;

    @ApiModelProperty("0-免费 1-收费")
    private String isCharge;

    private BigDecimal price;

    private String imgUrl;

}
