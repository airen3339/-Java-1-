package com.cy.systemManagement.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.systemManagement.menu.entity.MakeMenuTree;
import com.cy.systemManagement.menu.entity.Menu;
import com.cy.systemManagement.menu.service.MenuService;
import com.cy.systemManagement.role.entity.Role;
import com.cy.systemManagement.role.entity.RoleAssignParam;
import com.cy.systemManagement.role.entity.RoleParam;
import com.cy.systemManagement.role.entity.RolePermissionVo;
import com.cy.systemManagement.role.mapper.RoleMapper;
import com.cy.systemManagement.role.service.RoleService;
import com.cy.systemManagement.role_menu.entity.RoleMenu;
import com.cy.systemManagement.role_menu.service.RoleMenuService;
import com.cy.systemManagement.user.entity.User;
import com.cy.systemManagement.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2021-12-22 16:27:37
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 查询角色列表
     * @param roleParam
     * @return
     */
    @Override
    public IPage<Role> list(RoleParam roleParam) {
        IPage<Role> page = new Page<>();
        page.setCurrent(roleParam.getCurrentPage());
        page.setSize(roleParam.getPageSize());

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(roleParam.getRoleName())){
            queryWrapper.lambda().like(Role::getRoleName,roleParam.getRoleName());
        }
        return this.page(page,queryWrapper);
    }

    /**
     * 分配权限树数据回显查询
     *
     * @param param
     * @return
     */
    @Override
    public RolePermissionVo getAssignTree(RoleAssignParam param) {
        //根据用户id查询当前用户信息
        User user = userService.getById(param.getUserId());
        //查询当前用户的所有权限信息,如果是超级管理员，全部权限
        List<Menu> menuList = null;
        if("1".equals(user.getIsAdmin())){ //如果是超级管理员
            menuList = menuService.list();
        }else{ //不是超级管理员，根据用户id查询权限信息
            menuList = menuService.getMenuListByUserId(param.getUserId());
        }
        //组装树
        List<Menu> menus = MakeMenuTree.makeTree(menuList, 0L);

        //根据角色id查询角色原来的权限信息
        List<Long> ids = new ArrayList<>();
        List<Menu> listByRoleId = menuService.getMenuListByRoleId(param.getRoleId());
        Optional.ofNullable(menuList).orElse(new ArrayList<>()).stream().filter(Objects::nonNull).forEach(item ->{
            Optional.ofNullable(listByRoleId).orElse(new ArrayList<>()).stream().filter(Objects::nonNull).forEach(dom ->{
                if(item.getMenuId().equals(dom.getMenuId())){
                    ids.add(dom.getMenuId());
                }
            });
        });
        RolePermissionVo vo = new RolePermissionVo();
        vo.setListMenu(menus);
        vo.setCheckList(ids.toArray());
        return vo;
    }

    /**
     * 分配权限保存
     *
     * @param roleId
     * @param ids
     */
    @Override
    @Transactional
    public void saveAssignRole(Long roleId, List<Long> ids) {
        //保存权限之前，需要 把原来的权限删除
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoleId,roleId);
        roleMenuService.remove(query);
        //保存新的权限
        roleMenuService.saveRoleMenu(roleId,ids);
    }
}
