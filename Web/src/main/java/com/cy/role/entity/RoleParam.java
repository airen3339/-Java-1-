package com.cy.role.entity;

import lombok.Data;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 角色参数类
 * @date 2021-12-22 16:35:18
 */
@Data
public class RoleParam {

    //页容量
    private Long pageSize;
    //当前页
    private Long currentPage;
    //角色名称
    private String roleName;
}
