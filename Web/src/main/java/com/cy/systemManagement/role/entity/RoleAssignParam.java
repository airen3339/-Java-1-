package com.cy.systemManagement.role.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2022-01-13 19:11:30
 */
@Data
public class RoleAssignParam {

    /**
     * 用户id
     */
    @NotNull
    private Long userId;

    /**
     * 角色id
     */
    @NotNull
    private Long roleId;
}
