package com.cy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * 员工分页
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 员工分页
 * @date 2021-12-20 20:16:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserParam {

    /**
     * 页容量
     * @required
     */
    @Min(value = 10)
    private Long pageSize;

    /**
     * 当前页
     * @required
     */
    @Min(value = 1)
    private Long currentPage;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 电话
     */
    private String phone;
}
