package com.zxh.backsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxh.backsystem.entity.Menu;
import com.zxh.backsystem.service.IMenuService;
import com.zxh.backsystem.vo.MenuVo;
import com.zxh.backsystem.vo.PageVo;
import com.zxh.backsystem.vo.SelectedMenus;
import com.zxh.common.common.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxh
 * @since 2023-11-21
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    /**
     * 新增
     *
     * @param menu
     * @return
     */
    @PostMapping("/save")
    public ResultData save(@RequestBody Menu menu) {
        menuService.saveMenu(menu);
        return ResultData.ok();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultData delete(@PathVariable("id") Integer id) {
        menuService.deleteMneu(id);
        return ResultData.ok();
    }

    /*
     **修改
     */
    @PutMapping("/update")
    public ResultData update(@RequestBody Menu menu) {
        System.out.println(menu.toString());
        menuService.updateById(menu);
        return ResultData.ok();
    }

    @GetMapping("/{id}")
    public ResultData getMenu(@PathVariable("id") Integer id) {
        Menu menu = menuService.getById(id);
        return ResultData.ok().setData(menu);
    }

    @PostMapping("/list")
    public ResultData getMenuList(@RequestBody PageVo page) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        MenuVo menuVo = menuService.selectPage(new Page<>(page.getPage(), page.getSize()), queryWrapper);
        return ResultData.ok().setData(menuVo);
    }

    @PostMapping("/all/list")
    public ResultData getMenuList() {
        List<SelectedMenus> list = menuService.findMenus();
        return ResultData.ok().setData(list);
    }


    @GetMapping("/menu/{id}")
    public ResultData getMenusById(@PathVariable Integer id) {
        List<SelectedMenus> list = menuService.getMenusById(id);
        return ResultData.ok().setData(list);
    }

    /**
     * 获取课程菜单路由
     */
    @GetMapping("/menu/course")
    public ResultData getCourseMenu(){
        List<Menu> list = menuService.getCourseMenu();
        return ResultData.ok().setData(list);
    }
}
