package com.cy.parkManagement.park_list.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.parkManagement.park_list.entity.ParkList;
import com.cy.parkManagement.park_list.entity.ParkListParam;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-19 13:34:59
 */
public interface ParkListService extends IService<ParkList> {
    /**
     * 分页查询车位列表
     * @param parkListParam
     * @return
     */
    IPage<ParkList> getParkList(ParkListParam parkListParam);
}
