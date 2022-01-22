package com.cy.feeManagement.FeePower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.feeManagement.FeePower.entity.FeePower;
import org.apache.ibatis.annotations.Param;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-21 14:15:21
 */
public interface FeePowerMapper extends BaseMapper<FeePower> {
    /**
     * @param page
     * @param userName
     * @param houseNum
     * @return
     */
    IPage<FeePower> getFeePowerList(IPage<FeePower> page, @Param("userName") String userName, @Param("houseNum") String houseNum);
}
