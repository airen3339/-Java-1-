package com.cy.feeManagement.FeePower.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.feeManagement.FeePower.entity.FeePower;
import com.cy.feeManagement.FeePower.entity.FeePowerParam;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-21 14:16:34
 */
public interface FeePowerService extends IService<FeePower> {
    /**
     * 新增电费
     * @param feePower
     */
    int saveFeePower(FeePower feePower);


    /**
     * 编辑电费
     * @param feePower
     */
    boolean updateFeePower(FeePower feePower);

    /**
     * 查询列表
     * @param feePowerParam
     * @return
     */
    IPage<FeePower> getFeePowerList(FeePowerParam feePowerParam);
}
