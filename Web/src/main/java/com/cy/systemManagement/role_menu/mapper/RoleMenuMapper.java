package com.cy.systemManagement.role_menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.systemManagement.role_menu.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2022-01-13 18:41:24
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 保存权限
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean saveRoleMenu(@Param("roleId") Long roleId, @Param("menuIds")List<Long> menuIds);
}
