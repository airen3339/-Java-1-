package com.cy.parkManagement.park_list.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.parkManagement.park_list.entity.ParkList;
import com.cy.parkManagement.park_list.entity.ParkListParam;
import com.cy.parkManagement.park_list.mapper.ParkListMapper;
import com.cy.parkManagement.park_list.service.ParkListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-19 13:35:23
 */
@Service
public class ParkListServiceImpl extends ServiceImpl<ParkListMapper, ParkList> implements ParkListService {
    /**
     * 分页查询车位列表
     *
     * @param parkListParam
     * @return
     */
    @Override
    public IPage<ParkList> getParkList(ParkListParam parkListParam) {
        //构造分页查询条件
        QueryWrapper<ParkList> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parkListParam.getParkName())){
            query.lambda().like(ParkList::getParkName,parkListParam.getParkName());
        }
        if(StringUtils.isNotEmpty(parkListParam.getParkType())){
            query.lambda().eq(ParkList::getParkType,parkListParam.getParkType());
        }
        if(StringUtils.isNotEmpty(parkListParam.getParkStatus())){
            query.lambda().eq(ParkList::getParkStatus,parkListParam.getParkStatus());
        }
        query.lambda().orderByAsc(ParkList::getParkNum);
        //构造分页对象
        IPage<ParkList> page = new Page<>();
        page.setCurrent(parkListParam.getCurrentPage());
        page.setSize(parkListParam.getPageSize());
        return this.baseMapper.selectPage(page,query);
    }
}
