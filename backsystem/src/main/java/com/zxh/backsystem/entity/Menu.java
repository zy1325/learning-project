package com.zxh.backsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author zxh
 * @since 2023-11-21
 */
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "")
@Data
@ToString
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String path;

    private Integer parentId;

    private String menuLevel;

    private String component;

}
