package com.cy.feeManagement.FeeWater.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.feeManagement.FeeWater.entity.FeeWater;
import org.apache.ibatis.annotations.Param;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-21 14:15:21
 */
public interface FeeWaterMapper extends BaseMapper<FeeWater> {
    /**
     * @param page
     * @param userName
     * @param houseNum
     * @return
     */
    IPage<FeeWater> getFeeWaterList(IPage<FeeWater> page, @Param("userName") String userName, @Param("houseNum") String houseNum);
}
