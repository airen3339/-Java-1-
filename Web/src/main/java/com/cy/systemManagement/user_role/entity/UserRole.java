package com.cy.systemManagement.user_role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.valid.getRoleByUserId;
import com.cy.valid.saveRole;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 用户角色实体类
 * @date 2022-01-16 19:41:33
 */
@Data
@TableName("sys_user_role")
public class UserRole implements Serializable {
    /**
     * 主键Id
     */
    @TableId(type = IdType.AUTO)
    private Long userRoleId;

    /**
     * 用户Id
     */
    @NotNull(groups = {getRoleByUserId.class})
    private Long userId;

    /**
     * 角色Id
     */
    @NotNull(groups = {saveRole.class})
    private Long roleId;
}
