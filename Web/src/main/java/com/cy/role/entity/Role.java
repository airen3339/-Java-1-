package com.cy.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    @TableId(type = IdType.AUTO)
    private Long roleId;
    //角色名称
    private String roleName;
    //备注
    private String remark;
}
