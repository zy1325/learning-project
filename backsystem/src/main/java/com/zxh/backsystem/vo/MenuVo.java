package com.zxh.backsystem.vo;

import com.zxh.backsystem.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    private List<Menu> menus;

    private Integer total;
}
