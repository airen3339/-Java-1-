package com.cy.systemManagement.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.systemManagement.role.entity.Role;
import com.cy.systemManagement.role.entity.RoleAssignParam;
import com.cy.systemManagement.role.entity.RoleParam;
import com.cy.systemManagement.role.entity.RolePermissionVo;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2021-12-22 16:27:10
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询角色列表
     * @param roleParam
     * @return
     */
    IPage<Role> list(RoleParam roleParam);


    /**
     * 分配权限树数据回显查询
     * @param param
     * @return
     */
    RolePermissionVo getAssignTree(RoleAssignParam param);


    /**
     * 分配权限保存
     * @param roleId
     * @param ids
     */
    void saveAssignRole(Long roleId, List<Long> ids);
}
