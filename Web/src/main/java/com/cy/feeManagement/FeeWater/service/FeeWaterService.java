package com.cy.feeManagement.FeeWater.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.feeManagement.FeeWater.entity.FeeWater;
import com.cy.feeManagement.FeeWater.entity.FeeWaterParam;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-21 14:16:34
 */
public interface FeeWaterService extends IService<FeeWater> {
    /**
     * 新增水费
     * @param feePower
     */
    int saveFeePower(FeeWater feePower);


    /**
     * 编辑水费
     * @param feePower
     */
    boolean updateFeePower(FeeWater feePower);

    /**
     * 查询列表
     * @param feePowerParam
     * @return
     */
    IPage<FeeWater> getFeeWaterList(FeeWaterParam feePowerParam);
}
