package com.cy.homeManagement.house_building.entity;

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
 * @description: 房屋栋数实体类
 * @date 2022-01-17 11:11:07
 */

@Data
@TableName("house_building")
public class HouseBuilding implements Serializable {
    /**
     * 栋数Id
     */
    @TableId(type= IdType.AUTO)
    private Long buildId;

    /**
     * 栋数类型： 0：普通房 1：电梯房
     */
    @listValue(values = {"0", "1"})
    private String type;

    /**
     * 栋数名称
     */
    @NotBlank
    private String buildName;

    /**
     * 序号
     */
    private Long orderNum;
}

