package com.cy.homeManagement.house_unit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 房屋单元实体类
 * @date 2022-01-17 17:37:39
 */
@Data
@TableName("house_unit")
public class HouseUnit implements Serializable {
    /**
     * 单元Id
     */
    @TableId(type = IdType.AUTO)
    private Long unitId;

    /**
     * 栋数Id
     */
    @NotNull
    private Long buildId;

    /**
     * 单元名字
     */
    @NotBlank
    private String unitName;

    /**
     * 栋数名字
     */
    @TableField(exist = false)
    private String buildName;
}
