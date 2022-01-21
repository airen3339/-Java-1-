package com.cy.parkManagement.park_list.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 车位分页列表参数类
 * @date 2022-01-19 13:37:21
 */
@Data
public class ParkListParam {
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

    /**
     * 车位名称
     */
    private String parkName;

    /**
     * 车位使用状态
     */
    private String parkStatus;

    /**
     * 车位类型
     */
    private String parkType;
}
