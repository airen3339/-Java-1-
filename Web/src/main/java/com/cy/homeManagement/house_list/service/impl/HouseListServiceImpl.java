package com.cy.homeManagement.house_list.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.homeManagement.house_list.entity.HouseList;
import com.cy.homeManagement.house_list.entity.ListParam;
import com.cy.homeManagement.house_list.mapper.HouseListMapper;
import com.cy.homeManagement.house_list.service.HouseListService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 21:53:24
 */
@Service
public class HouseListServiceImpl extends ServiceImpl<HouseListMapper, HouseList> implements HouseListService {
    /**
     * 房屋列表查询
     *
     * @param param
     * @return
     */
    @Override
    public IPage<HouseList> getList(ListParam param) {
        IPage<HouseList> page = new Page<>();
        page.setCurrent(param.getCurrentPage());
        page.setSize(param.getPageSize());
        return this.baseMapper.getList(page,param.getBuildName(),param.getUnitName(),param.getHouseNum(),param.getStatus());
    }

    /**
     * 新增房屋
     *
     * @param houseList
     * @return
     */
    @Override
    public int saveHouseList(HouseList houseList) {
        int saveStatus = 1;
        QueryWrapper<HouseList> houseListQueryWrapper = new QueryWrapper<>();
        houseListQueryWrapper.lambda().eq(HouseList::getUnitId,houseList.getUnitId());
        List<HouseList> houseListByUnitId = this.baseMapper.getHouseListByUnitId(houseList.getUnitId());
        for (HouseList houseList1: houseListByUnitId) {
            if (houseList1.getHouseNum().equals(houseList.getHouseNum())){
                return saveStatus = 2;
            }
        }
        saveStatus = this.baseMapper.insert(houseList);
        return saveStatus;
    }

    @Override
    public List<HouseList> editHouseList(HouseList houseList) {
        return this.baseMapper.editHouseList(houseList.getUnitId(),houseList.getBuildId());
    }
}
