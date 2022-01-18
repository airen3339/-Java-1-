package com.cy.homeManagement.house_building.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.homeManagement.house_building.entity.BuildingParam;
import com.cy.homeManagement.house_building.entity.HouseBuilding;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 11:24:11
 */
public interface HouseBuildingService extends IService<HouseBuilding> {
    /**
     * 栋数列表查询
     * @param param
     * @return
     */
    IPage<HouseBuilding> getList(BuildingParam param);
}
