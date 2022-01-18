package com.cy.homeManagement.house_list.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.homeManagement.house_list.entity.HouseList;
import org.apache.ibatis.annotations.Param;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 21:52:12
 */
public interface HouseListMapper extends BaseMapper<HouseList> {
    IPage<HouseList> getList(IPage<HouseList> page, @Param("buildName") String buildName, @Param("unitName") String unitName, @Param("houseNum") String houseNum, @Param("status") String status);
}
