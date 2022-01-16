package com.cy.systemManagement.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.valid.listValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 员工表
 * @date 2021-12-20 18:11:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
@EqualsAndHashCode
public class User implements Serializable {

    /**
     * 用户ID
     * @ignore
     */
    @TableId(type= IdType.AUTO)
    private Long userId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 姓名
     */
    @NotBlank
    private String userName;

    /**
     * 电话
     */
    @NotBlank
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$",
            message = "手机号码格式不正确"
    )
    private String phone;

    /**
     * 性别 0：女 1：男
     */
    @NotBlank
    @listValue(values = {"0","1"})
    private String sex;

    /**
     * 身份证
     */
    @NotBlank
    @Pattern(regexp = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$",
            message = "身份证号码格式不正确"
    )
    private String idCard;

    /**
     * 是否是管理员 0：不是 1：是
     */
    //@listValue(values = {"0","1"})
    //@NotBlank
    private String isAdmin;

    /**
     * 0：在职  1：离职
     */
    @NotBlank
    @listValue(values = {"0","1"})
    private String status;

    /**
     * 0：启用 1：禁用
     */
    @NotBlank
    @listValue(values = {"0","1"})
    private String isUsed;

}
