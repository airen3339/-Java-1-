package com.cy.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    //设置主键自增
    @TableId(type= IdType.AUTO)
    private Long userId;
    //登录名
    private String loginName;
    //登录密码
    private String password;
    //姓名
    private String userName;
    //电话
    @NotBlank
    private String phone;
    //性别 0：女 1：男
    private String sex;
    //身份证
    private String idCard;
    //是否是管理员 0：不是 1：是
    private String isAdmin;
    //0：在职  1：离职
    private String status;
    //0：启用 1：禁用
    private String isUsed;

}
