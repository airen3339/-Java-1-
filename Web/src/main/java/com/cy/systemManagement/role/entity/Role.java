package com.cy.systemManagement.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 角色管理
 * @date 2021-12-22 15:10:21
 * @date 2021-12-22 16:25:35
 */
@Data
@TableName("sys_role")
public class Role implements Serializable {
    /**
     * 角色ID
     * @ignore
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank
    private String roleName;

    /**
     * 备注
     */
    private String remark;
}
