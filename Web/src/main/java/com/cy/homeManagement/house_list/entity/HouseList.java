package com.cy.homeManagement.house_list.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.valid.listValue;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 房屋列表实体类
 * @date 2022-01-17 21:48:49
 */
@Data
@TableName("house_list")
public class HouseList implements Serializable {
    /**
     * 房屋ID
     */
    @TableId(type = IdType.AUTO)
    private Long houseId;

    /**
     * 单元id
     */
    @NotNull
    private Long unitId;

    /**
     * 房屋面积编号
     */
    @NotBlank
    private String houseNum;

    /**
     * 房屋面积
     */
    @NotBlank
    private String houseArea;

    /**
     * 使用状态 0:未使用 1：已使用
     */
    @listValue(values = {"0","1"})
    private String status;

    /**
     * 栋数id
     */
    @TableField(exist = false)
    @NotNull
    private Long buildId;

    /**
     * 栋数名称
     */
    @TableField(exist = false)
    private String buildName;

    /**
     * 单元名称
     */
    @TableField(exist = false)
    private String unitName;
}
