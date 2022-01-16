package com.cy.systemManagement.role_menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.systemManagement.role_menu.entity.RoleMenu;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2022-01-13 18:43:32
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     * 保存权限
     * @param roleId
     * @param menuIds
     */
    void saveRoleMenu(Long roleId, List<Long> menuIds);
}
