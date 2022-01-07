package com.cy.menu.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.menu.entity.MakeMenuTree;
import com.cy.menu.entity.Menu;
import com.cy.menu.mapper.MenuMapper;
import com.cy.menu.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2022-01-06 15:26:51
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService  {
    /**
     * 获取菜单列表树数据
     */
    @Override
    public List<Menu> getList() {
        //构造查询条件
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().orderByAsc(Menu::getName);
        List<Menu> menus = this.baseMapper.selectList(query);
        //组装成树数据
        return MakeMenuTree.makeTree(menus, 0L);
    }

    /**
     * 查询上级菜单
     */
    @Override
    public List<Menu> getParentList() {
        //只查询目录和菜单 即只查询 0 和 1 的数据
        String[] types = {"0","1"};
        //构造查询条件
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().in(Menu::getType, Arrays.asList(types)).orderByAsc(Menu::getOrderNum);
        List<Menu> menus = this.baseMapper.selectList(query);
        //构造顶级菜单
        Menu menu = new Menu();
        menu.setMenuId(0L);
        menu.setParentId(-1L);
        menu.setMenuLabel("顶级菜单");
        menus.add(menu);
        //构造菜单树
        return MakeMenuTree.makeTree(menus, -1L);
    }
}
