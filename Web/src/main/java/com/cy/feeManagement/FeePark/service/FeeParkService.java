package com.cy.feeManagement.FeePark.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.feeManagement.FeePark.entity.FeePark;
import com.cy.feeManagement.FeePark.entity.FeeParkParam;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-22 10:38:21
 */
public interface FeeParkService extends IService<FeePark> {


    /**
     * 查询列表
     * @param feeParkParam
     * @return
     */
    IPage<FeePark> getFeeParkList(FeeParkParam feeParkParam);
}
