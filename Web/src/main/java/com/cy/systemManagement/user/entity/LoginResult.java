package com.cy.systemManagement.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 登录返回信息类
 * @date 2022-01-12 15:45:44
 */
@Data
public class LoginResult implements Serializable {
    /**
     * 用户Id
     */
    private Long userId;

    /**
     * token信息
     */
    private String token;

    /**
     * token有效时间(毫秒)
     */
    private Long expireTime;
}
