package com.cy.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.role.entity.Role;
import com.cy.role.entity.RoleParam;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2021-12-22 16:27:10
 */
public interface RoleService extends IService<Role> {
    IPage<Role> list(RoleParam roleParam);
}
