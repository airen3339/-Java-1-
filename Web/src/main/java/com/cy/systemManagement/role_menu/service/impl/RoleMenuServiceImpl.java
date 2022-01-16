package com.cy.systemManagement.role_menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.systemManagement.role_menu.entity.RoleMenu;
import com.cy.systemManagement.role_menu.mapper.RoleMenuMapper;
import com.cy.systemManagement.role_menu.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2022-01-13 18:43:59
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    /**
     * 保存权限
     *
     * @param roleId
     * @param menuIds
     */
    @Override
    public void saveRoleMenu(Long roleId, List<Long> menuIds) {
        this.baseMapper.saveRoleMenu(roleId, menuIds);
    }
}
