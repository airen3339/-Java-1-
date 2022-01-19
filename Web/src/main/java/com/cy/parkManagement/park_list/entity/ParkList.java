package com.cy.parkManagement.park_list.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.valid.listValue;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 车位管理实体类
 * @date 2022-01-19 13:31:38
 */
@Data
@TableName("parking_list")
public class ParkList implements Serializable {
    /**
     * 车位Id
     */
    @TableId(type = IdType.AUTO)
    private Long parkId;

    /**
     * 车位类型 0：地上 1：地下
     */
    @listValue(values = {"0","1"})
    private String parkType;

    /**
     * 车位名称
     */
    @NotBlank
    private String parkName;

    /**
     * 使用状态 0：未使用 1：已使用
     */
    @listValue(values = {"0","1"})
    private String parkStatus;

    /**
     * 序号
     */
    private Long parkNum;
}
