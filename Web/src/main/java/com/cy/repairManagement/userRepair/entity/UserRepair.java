package com.cy.repairManagement.userRepair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-23 19:38:53
 */
@Data
@TableName("user_repair")
public class UserRepair implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long repairId;

    /**
     * 报修人id
     */
    @NotNull
    private Long userId;

    /**
     * 联系电话
     */
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$",message = "请填写正确的手机号")
    @NotBlank
    private String phone;

    /**
     * 维修地址
     */
    @NotBlank
    private String repairAddress;

    /**
     * 维修内容
     */
    @NotBlank
    private String repairContent;

    /**
     * 报修时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date commitTime;

    /**
     * 维修状态 0：未维修 1：已维修
     */
    private String status;
}
