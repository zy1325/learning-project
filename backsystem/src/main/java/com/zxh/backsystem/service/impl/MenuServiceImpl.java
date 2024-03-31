package com.zxh.backsystem.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxh.backsystem.entity.Menu;
import com.zxh.backsystem.entity.RoleMenu;
import com.zxh.backsystem.mapper.MenuMapper;
import com.zxh.backsystem.mapper.RoleMenuMapper;
import com.zxh.backsystem.service.IMenuService;
import com.zxh.backsystem.vo.MenuVo;
import com.zxh.backsystem.vo.MenusVo;
import com.zxh.backsystem.vo.SelectedMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxh
 * @since 2023-11-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public String saveMenu(Menu menu) {
        this.save(menu);
        return "ok";
    }

    @Override
    public int deleteMneu(Integer id) {
        this.removeById(id);
        return 1;
    }

    @Override
    public MenuVo selectPage(Page<Menu> menuPage, QueryWrapper<Menu> queryWrapper) {
        Page<Menu> Pages = menuMapper.selectPage(menuPage, queryWrapper);
        Integer count = menuMapper.selectCount(queryWrapper);
        MenuVo menuVo = new MenuVo();
        menuVo.setMenus(Pages.getRecords());
        menuVo.setTotal(count);
        return menuVo;
    }

    @Override
    public List<SelectedMenus> findMenus() {
        List<Menu> list = this.list();
        //获取父菜单
        List<Menu> parentMenuList = list.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());
        //获取子菜单
        List<Menu> childrenMenuList = list.stream().filter(item -> item.getParentId() != 0).collect(Collectors.toList());

        List<SelectedMenus> menus = new ArrayList<>();

        for (Menu menu : parentMenuList) {
            SelectedMenus selectedMenu = new SelectedMenus();
            selectedMenu.setName(menu.getName());
            selectedMenu.setComponent(menu.getComponent());
            selectedMenu.setPath(menu.getPath());
            List<MenusVo> childrenList = new ArrayList<>();
            for (Menu childMenu : childrenMenuList) {
                if (childMenu.getParentId() == menu.getId()) {
                    MenusVo menusVo = new MenusVo();
                    menusVo.setComponent(childMenu.getComponent());
                    menusVo.setName(childMenu.getName());
                    menusVo.setPath(childMenu.getPath());
                    childrenList.add(menusVo);
                }
            }
            if (!childrenList.isEmpty()) {
                selectedMenu.setChildren(childrenList);
            }
            menus.add(selectedMenu);
        }
        return menus;
    }


    private List<SelectedMenus> getSelectedMenus(List<Menu> list) {
        //获取父菜单
        List<Menu> parentMenuList = list.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());
        //获取子菜单
        List<Menu> childrenMenuList = list.stream().filter(item -> item.getParentId() != 0).collect(Collectors.toList());

        List<SelectedMenus> menus = new ArrayList<>();


        for (Menu menu : parentMenuList) {
            SelectedMenus selectedMenu = new SelectedMenus();
            selectedMenu.setName(menu.getName());
            selectedMenu.setComponent(menu.getComponent());
            selectedMenu.setPath(menu.getPath());
            selectedMenu.setId(menu.getId());
            List<MenusVo> childrenList = new ArrayList<>();
            for (Menu childMenu : childrenMenuList) {
                if (childMenu.getParentId() == menu.getId()) {
                    MenusVo menusVo = new MenusVo();
                    menusVo.setComponent(childMenu.getComponent());
                    menusVo.setName(childMenu.getName());
                    menusVo.setPath(childMenu.getPath());
                    menusVo.setId(childMenu.getId());
                    childrenList.add(menusVo);
                }
            }
            if (!childrenList.isEmpty()) {
                selectedMenu.setChildren(childrenList);
            }
            menus.add(selectedMenu);
        }
        return menus;
    }

    @Override
    public List<SelectedMenus> getMenusById(Integer id) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<RoleMenu>().eq("role_id", id);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(queryWrapper);
        List<Integer> ids = roleMenus.stream().map(menu -> menu.getMenuId()).collect(Collectors.toList());
        List<Menu> menus = menuMapper.selectBatchIds(ids);
        List<SelectedMenus> selectedMenus = getSelectedMenus(menus);
        return selectedMenus;
    }

    @Override
    public List<Menu> getCourseMenu() {
        QueryWrapper<Menu> eq = new QueryWrapper<Menu>().eq("name", "具体课程");
        Menu detailMenu = this.getOne(eq);
        QueryWrapper<Menu> courseMneuWrapper = new QueryWrapper<Menu>().eq("parent_id", detailMenu.getId());
        List<Menu> menus = menuMapper.selectList(courseMneuWrapper);
        return menus;
    }
}
