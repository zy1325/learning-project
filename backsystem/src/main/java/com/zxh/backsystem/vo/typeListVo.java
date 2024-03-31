package com.zxh.backsystem.vo;

import lombok.Data;

import java.util.List;

@Data
public class typeListVo {
    private Integer id;

    private String name;

    private Integer pid;

    private List<typeListVo> children;
}
