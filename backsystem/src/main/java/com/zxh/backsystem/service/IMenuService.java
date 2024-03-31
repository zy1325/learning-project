package com.zxh.backsystem.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxh.backsystem.entity.Menu;
import com.zxh.backsystem.vo.MenuVo;
import com.zxh.backsystem.vo.SelectedMenus;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxh
 * @since 2023-11-21
 */
public interface IMenuService extends IService<Menu> {

    String saveMenu(Menu menu);

    int deleteMneu(Integer id);

    MenuVo selectPage(Page<Menu> menuPage, QueryWrapper<Menu> queryWrapper);

    List<SelectedMenus> findMenus();


    List<SelectedMenus> getMenusById(Integer id);

    /**
     * 获取课程路由
     * @return
     */
    List<Menu> getCourseMenu();
}
