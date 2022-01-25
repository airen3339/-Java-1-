package com.cy.systemManagement.menu.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.systemManagement.menu.entity.MakeMenuTree;
import com.cy.systemManagement.menu.entity.Menu;
import com.cy.systemManagement.menu.mapper.MenuMapper;
import com.cy.systemManagement.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MenuService menuService;

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

    /**
     * 根据用户id查询权限列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Menu> getMenuListByUserId(Long userId) {
        return this.baseMapper.getMenuListByUserId(userId);
    }

    /**
     * 根据角色id查询权限信息
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> getMenuListByRoleId(Long roleId) {
        return this.baseMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public int menuIsExist(Long parentId , String menuLabel) {
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.lambda().eq(Menu::getParentId,parentId);
        List<Menu> list = menuService.list(menuQueryWrapper);
        int flag = 0;
        for (Menu menu: list) {
            if (menu.getMenuLabel().equals(menuLabel)) {
                flag = 1;
                break;
            }
        }
        return flag;
    }

    /**
     * 根据业主的id查询菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<Menu> getMenuByUserIdForLiveUser(Long userId) {
        return this.baseMapper.getMenuByUserIdForLiveUser(userId);
    }
}
