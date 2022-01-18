package com.cy.systemManagement.role.entity;

import com.cy.systemManagement.menu.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 角色权限信息返回类
 * @date 2022-01-13 19:12:25
 */
@Data
public class RolePermissionVo {

    /**
     * 当前登录系统用户的菜单数据
     */
    List<Menu> listMenu = new ArrayList<>();

    /**
     * 原来分配的菜单
     */
    private Object[] checkList;
}
