package com.cy.systemManagement.role_menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 角色菜单类
 * @date 2022-01-13 18:38:56
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {
    /**
     * 角色菜单ID
     */
    @TableId(type = IdType.AUTO)
    private Long roleMenuId;

    /**
     *  角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;
}
