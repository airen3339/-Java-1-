package com.cy.systemManagement.user.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-25 00:17:15
 */
@Data
public class ChangePassword implements Serializable {
    /**
     * 用户id
     */
    @NotNull
    private Long userId;
    /**
     * 旧密码
     */
    @NotBlank
    private String oldPassword;
    /**
     * 新密码
     */
    @NotBlank
    private String newPassword;
}
