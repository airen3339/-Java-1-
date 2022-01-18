package com.cy.homeManagement.house_unit.serveie.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.homeManagement.house_unit.entity.HouseUnit;
import com.cy.homeManagement.house_unit.entity.HouseUnitParam;
import com.cy.homeManagement.house_unit.mapper.HouseUnitMapper;
import com.cy.homeManagement.house_unit.serveie.HouseUnitService;
import org.springframework.stereotype.Service;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 17:42:04
 */
@Service
public class HouseUnitServiceImpl extends ServiceImpl<HouseUnitMapper, HouseUnit> implements HouseUnitService {
    /**
     * 查询房屋单元列表
     *
     * @param param
     * @return
     */
    @Override
    public IPage<HouseUnit> getList(HouseUnitParam param) {
        IPage<HouseUnit> page = new Page<>();
        page.setCurrent(param.getCurrentPage());
        page.setSize(param.getPageSize());
        return this.baseMapper.getList(page,param.getBuildName(),param.getUnitName());
    }
}
