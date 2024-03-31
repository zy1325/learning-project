package com.zxh.backsystem.vo;

import com.zxh.backsystem.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class SelectedMenus {

    private int id;

    private String name;

    private String path;

    private String component;

    private List<MenusVo> children;
}
