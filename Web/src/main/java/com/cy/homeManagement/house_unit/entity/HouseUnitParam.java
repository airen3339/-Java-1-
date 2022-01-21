package com.cy.homeManagement.house_unit.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 房屋单元分页查询参数
 * @date 2022-01-17 17:38:40
 */
@Data
public class HouseUnitParam implements Serializable {
    /**
     * 栋数名字
     */
    private String buildName;

    /**
     * 单元名字
     */
    private String unitName;

    /**
     * 当前页
     */
    @Min(1)
    @NotNull
    private Long currentPage;

    /**
     * 页容量
     */
    @NotNull
    private Long pageSize;
}
