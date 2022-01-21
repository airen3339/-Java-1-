package com.cy.liveManagement.live_role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 业主角色关系表
 * @date 2022-01-20 14:33:34
 */
@Data
@TableName("live_role")
public class LiveRole implements Serializable {
    /**
     * 业主角色Id
     */
    @TableId(type = IdType.AUTO)
    private Long livRoleId;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 业主Id
     */
    private Long userId;

}
