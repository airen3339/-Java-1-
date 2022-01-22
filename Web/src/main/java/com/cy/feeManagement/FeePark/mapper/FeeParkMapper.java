package com.cy.feeManagement.FeePark.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.feeManagement.FeePark.entity.FeePark;
import org.apache.ibatis.annotations.Param;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-22 10:38:05
 */
public interface FeeParkMapper extends BaseMapper<FeePark> {

    /**
     * 查询列表
     * @param page
     * @param userName
     * @param parkName
     * @return
     */
    IPage<FeePark> getFeeParkList(IPage<FeePark> page, @Param("userName") String userName, @Param("parkName") String parkName);
}
