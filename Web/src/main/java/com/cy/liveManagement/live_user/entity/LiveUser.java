package com.cy.liveManagement.live_user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.valid.listValue;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 业主表
 * @date 2022-01-20 12:05:55
 */
@Data
@TableName("live_user")
public class LiveUser implements UserDetails,Serializable {
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 登录账户
     */
    @NotBlank
    private String username;

    /**
     * 联系电话
     */
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$",
            message = "手机号格式错误")
    @NotBlank
    private String phone;

    /**
     * 性别 0：男 1：女
     */
    @listValue(values = {"0","1"})
    @NotBlank
    private String sex;

    /**
     * 姓名
     */
    @NotBlank
    private String loginName;

    /**
     * 登录密码
     */
    @NotBlank
    private String password;

    /**
     * 状态 0:启用 1：禁用
     */
    @listValue(values = {"0","1"})
    private String status;

    /**
     * 角色id
     */
    @TableField(exist =  false)
    private Long roleId;

    /**
     * 房屋id
     */
    @TableField(exist =  false)
    private Long houseId;

    /**
     * 车位id
     */
    @TableField(exist =  false)
    private Long parkId;

    /**
     * 房屋编码
     */
    @TableField(exist =  false)
    private String houseNum;

    /**
     * 房屋面积
     */
    @TableField(exist =  false)
    private String houseArea;

    /**
     * 单元名字
     */
    @TableField(exist =  false)
    private String unitName;

    /**
     * 栋数名字
     */
    @TableField(exist =  false)
    private String buildName;

    /**
     * 车位名字
     */
    @TableField(exist =  false)
    private String parkName;

    /**
     * 房屋使用状态
     */
    @TableField(exist =  false)
    private String useStatus;

    /**
     * 车位使用状态
     */
    @TableField(exist =  false)
    private String liveStatue;

    //下面的字段，属于spring security的UserDetails的字段

    /**
     * 帐户是否过期(1 未过期，0已过期)
     */
    private boolean isAccountNonExpired = true;

    /**
     * 帐户是否被锁定(1 未锁定，0已锁定)
     */
    private boolean isAccountNonLocked = true;

    /**
     * 密码是否过期(1 未过期，0已过期)
     */
    private boolean isCredentialsNonExpired = true;

    /**
     * 帐户是否可用(1 可用，0 删除用户)
     */
    private boolean isEnabled = true;

    /**
     * 由于authorities不是数据库里面的字段，所以要排除他，不然mybatis-plus找不到该字段会报错
     */
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

}
