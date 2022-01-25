package com.cy.feeManagement.FeePark.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-22 10:43:13
 */
@Data
public class FeeParkParam {
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
     * 业主名字
     */
    private String userName;

    /**
     * 车位id
     */
    private String parkName;

    private Long userId;
}
