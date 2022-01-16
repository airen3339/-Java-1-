package com.cy.systemManagement.user.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 登录类
 * @date 2022-01-12 15:44:12
 */
@Data
public class LoginParam implements Serializable {

    /**
     * 登录名
     */
    @NotBlank
    private String username;

    /**
     * 登录密码
     */
    @NotBlank
    private String password;


    /**
     * 登录类型
     */
    @NotBlank
    private String userType;
}
