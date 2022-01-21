package com.cy.homeManagement.house_building.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 栋数分页参数
 * @date 2022-01-17 11:36:46
 */
@Data
public class BuildingParam implements Serializable {

    /**
     * 页容量
     */
    @NotNull
    private Long pageSize;

    /**
     * 当前页
     */
    @Min(1)
    @NotNull
    private Long currentPage;

    /**
     * 楼栋名称
     */
    private String buildName;

    /**
     * 楼栋类型
     */
    private String type;
}
