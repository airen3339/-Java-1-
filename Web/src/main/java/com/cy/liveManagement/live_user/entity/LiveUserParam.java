package com.cy.liveManagement.live_user.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 分页参数类
 * @date 2022-01-20 18:15:39
 */
@Data
public class LiveUserParam implements Serializable {

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
     *
     */
    private String loginName;

    /**
     * 电话
     */
    private String phone;
}
