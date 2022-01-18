package com.cy.homeManagement.house_building.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.homeManagement.house_building.entity.BuildingParam;
import com.cy.homeManagement.house_building.entity.HouseBuilding;
import com.cy.homeManagement.house_building.mapper.HouseBuildingMapper;
import com.cy.homeManagement.house_building.service.HouseBuildingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 11:25:30
 */
@Service
public class HouseBuildingServiceImpl extends ServiceImpl<HouseBuildingMapper, HouseBuilding> implements HouseBuildingService {
    /**
     * 栋数列表查询
     *
     * @param param
     * @return
     */
    @Override
    public IPage<HouseBuilding> getList(BuildingParam param) {
        //构造查询条件
        QueryWrapper<HouseBuilding> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(param.getBuildName())){
            query.lambda().like(HouseBuilding::getBuildName,param.getBuildName());
        }
        if(StringUtils.isNotEmpty(param.getType())){
            query.lambda().like(HouseBuilding::getType,param.getType());
        }
        //构造分页对象
        IPage<HouseBuilding> page = new Page<>();
        page.setCurrent(param.getCurrentPage());
        page.setSize(param.getPageSize());

        return this.baseMapper.selectPage(page,query);
    }
}
