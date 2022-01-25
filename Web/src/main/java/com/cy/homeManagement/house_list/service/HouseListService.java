package com.cy.homeManagement.house_list.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.homeManagement.house_list.entity.HouseList;
import com.cy.homeManagement.house_list.entity.ListParam;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 21:53:01
 */
public interface HouseListService extends IService<HouseList> {
    /**
     * 房屋列表查询
     * @param param
     * @return
     */
    IPage<HouseList> getList(ListParam param);

    /**
     * 新增房屋
     * @param houseList
     * @return
     */
    int saveHouseList(HouseList houseList);

    List<HouseList> editHouseList(HouseList houseList);
}
