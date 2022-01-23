package com.cy.repairManagement.userRepair.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-23 19:41:57
 */
@Data
public class UserRepairParam implements Serializable {

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
     * 投诉人id
     */
    private Long userId;

    /**
     * 维修内容
     */
    private String repairContent;
}
