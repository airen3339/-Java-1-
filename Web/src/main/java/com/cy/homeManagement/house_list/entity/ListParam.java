package com.cy.homeManagement.house_list.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 房屋列表分页参数类
 * @date 2022-01-17 21:51:03
 */
@Data
public class ListParam implements Serializable {
    /**
     * 栋数名称
     */
    private String buildName;

    /**
     * 单元名称
     */
    private String unitName;

    /**
     * 使用状态
     */
    private String status;

    /**
     * 房屋编号
     */
    private String houseNum;

    /**
     * 当前页数
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
