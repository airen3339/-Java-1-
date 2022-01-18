package com.cy.homeManagement.house_unit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.homeManagement.house_unit.entity.HouseUnit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 17:40:38
 */
@Mapper
public interface HouseUnitMapper extends BaseMapper<HouseUnit> {

    /**
     * 查询房屋单元列表
     * @param page
     * @param buildName
     * @param unitName
     * @return
     */
    IPage<HouseUnit> getList(IPage<HouseUnit> page, @Param("buildName") String buildName, @Param("unitName") String unitName);
}
