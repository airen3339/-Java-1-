package com.cy.homeManagement.house_unit.serveie;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.homeManagement.house_unit.entity.HouseUnit;
import com.cy.homeManagement.house_unit.entity.HouseUnitParam;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 17:41:37
 */
public interface HouseUnitService extends IService<HouseUnit> {
    /**
     * 查询房屋单元列表
     * @param param
     * @return
     */
    IPage<HouseUnit> getList(HouseUnitParam param);
}
