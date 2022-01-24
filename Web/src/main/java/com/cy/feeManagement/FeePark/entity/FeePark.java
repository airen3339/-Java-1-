package com.cy.feeManagement.FeePark.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cy.valid.feeAddOrEdit;
import com.cy.valid.feePay;
import com.cy.valid.listValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 停车费
 * @date 2022-01-22 10:32:32
 */
@Data
public class FeePark implements Serializable {
    @TableId(type = IdType.AUTO)
    @NotNull(groups = {feePay.class})
    private Long parkFeeId;

    /**
     * 车位id
     */
    @NotNull(groups = {feeAddOrEdit.class})
    private Long parkId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 所属月份
     */
    @NotBlank(groups = {feeAddOrEdit.class})
    private String payParkMonth;

    /**
     * 缴费金额
     */
    @DecimalMin(value = "0")
    @NotNull(groups = {feeAddOrEdit.class})
    private BigDecimal payParkMoney;


    /**
     *  缴费状态
     */
    @listValue(values = {"0","1"})
    @NotBlank(groups = {feePay.class,feeAddOrEdit.class})
    private String payParkStatus;

    /**
     * 缴费时间
     */

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date payParkTime;

    /**
     * 业主名字
     */
    @TableField(exist = false)
    private String loginName;

    /**
     * 业主手机号
     */
    @TableField(exist = false)
    private String phone;

    /**
     * 单元名字
     */
    @TableField(exist = false)
    private String parkName;

    /**
     * 房屋编号
     */
    @TableField(exist = false)
    private String parkType;

}
