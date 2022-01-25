package com.cy.feeManagement.FeePower.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-21 17:11:28
 */
@Data
public class FeePowerParam implements Serializable {
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
     * 房屋id
     */
    private String houseNum;

    private Long userId;
}
