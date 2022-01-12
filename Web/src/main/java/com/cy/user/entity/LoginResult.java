package com.cy.user.entity;

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
    private Long userId;
    private String token;
    private Long expireTime;
}
