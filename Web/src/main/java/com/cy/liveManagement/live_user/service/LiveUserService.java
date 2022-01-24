package com.cy.liveManagement.live_user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.liveManagement.live_park.entity.LivePark;
import com.cy.liveManagement.live_user.entity.AssignHouseParam;
import com.cy.liveManagement.live_user.entity.LiveUser;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-20 12:08:56
 */
public interface LiveUserService extends IService<LiveUser> {
    /**
     * 新增业主
     * @param liveUser
     */
    boolean saveLiveUser(LiveUser liveUser);


    /**
     * 业主列表
     * @param page
     * @param loginName
     * @param phone
     * @return
     */
    IPage<LiveUser> getLiveUserList(IPage<LiveUser> page, String loginName, String phone);


    /**
     * 编辑业主
     * @param liveUser
     */
    boolean editLiveUser(LiveUser liveUser);


    /**
     * 业主数据查询
     * @param userId
     * @return
     */
    LiveUser getUser(Long userId);

    /**
     * 分配房屋保存
     * @param param
     */
    boolean assignHouse(AssignHouseParam param);


    /**
     * 分配车位保存
     * @param livePark
     * @return
     */
    boolean assignSavePark(LivePark livePark);

    /**
     * 退房
     * @param param
     */
    boolean returnHouse(AssignHouseParam param);

    /**
     * 退车位
     * @param livePark
     */
    boolean returnPark(LivePark livePark);

    /**
     * 根据用户名查用户
     * @param username
     * @return
     */
    LiveUser loadUser(String username);
}
