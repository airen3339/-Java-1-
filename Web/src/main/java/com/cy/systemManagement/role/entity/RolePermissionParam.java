package com.cy.systemManagement.role.entity;

import lombok.Data;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 添加角色权限类
 * @date 2022-01-13 21:35:13
 */
@Data
public class RolePermissionParam {
    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 选中的权限Id
     */
    List<Long> idList;
}
