package com.cy.systemManagement.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 员工信息类
 * @date 2022-01-12 21:39:57
 */
@Data
public class UserInfo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 权限集合
     */
    private Object[] roles;
}
