package com.cy.liveManagement.live_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.homeManagement.house_list.entity.HouseList;
import com.cy.homeManagement.house_list.mapper.HouseListMapper;
import com.cy.liveManagement.live_house.entity.LiveHouse;
import com.cy.liveManagement.live_house.mapper.LiveHouseMapper;
import com.cy.liveManagement.live_park.entity.LivePark;
import com.cy.liveManagement.live_park.mapper.LiveParkMapper;
import com.cy.liveManagement.live_role.entity.LiveRole;
import com.cy.liveManagement.live_role.mapper.LiveRoleMapper;
import com.cy.liveManagement.live_user.entity.AssignHouseParam;
import com.cy.liveManagement.live_user.entity.LiveUser;
import com.cy.liveManagement.live_user.mapper.LiveUserMapper;
import com.cy.liveManagement.live_user.service.LiveUserService;
import com.cy.parkManagement.park_list.entity.ParkList;
import com.cy.parkManagement.park_list.mapper.ParkListMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-20 12:09:17
 */
@Service
public class LiveUserServiceImpl extends ServiceImpl<LiveUserMapper, LiveUser> implements LiveUserService {
    @Resource
    private LiveRoleMapper liveRoleMapper;
    @Resource
    private LiveHouseMapper liveHouseMapper;
    @Resource
    private HouseListMapper houseListMapper;
    @Resource
    private ParkListMapper parkListMapper;
    @Resource
    private LiveParkMapper liveParkMapper;

    /**
     * 新增业主
     *
     * @param liveUser
     */
    @Override
    @Transactional
    public boolean saveLiveUser(LiveUser liveUser) {
        this.baseMapper.insert(liveUser);
        LiveRole liveRole = new LiveRole();
        liveRole.setRoleId(liveUser.getRoleId());
        liveRole.setUserId(liveUser.getUserId());
        int insert = liveRoleMapper.insert(liveRole);
        return insert == 1;
    }

    /**
     * 业主列表
     *
     * @param page
     * @param loginName
     * @param phone
     * @return
     */
    @Override
    public IPage<LiveUser> getLiveUserList(IPage<LiveUser> page, String loginName, String phone) {
        return this.baseMapper.getLiveUserList(page, loginName, phone);
    }

    /**
     * 编辑业主
     *
     * @param liveUser
     */
    @Override
    @Transactional
    public boolean editLiveUser(LiveUser liveUser) {
        //1.更新业主表
        this.baseMapper.updateById(liveUser);
        //2.角色关联表的数据删除
        QueryWrapper<LiveRole> query = new QueryWrapper<>();
        query.lambda().eq(LiveRole::getUserId, liveUser.getUserId());
        //3.插入新的角色
        LiveRole liveRole = new LiveRole();
        liveRole.setRoleId(liveUser.getRoleId());
        liveRole.setUserId(liveUser.getUserId());
        int update = liveRoleMapper.update(liveRole, query);
        return update == 1;
    }

    /**
     * 业主数据查询
     *
     * @param userId
     * @return
     */
    @Override
    public LiveUser getUser(Long userId) {
        return this.baseMapper.getUser(userId);
    }

    /**
     * 分配房屋保存
     *
     * @param param
     */
    @Override
    @Transactional
    public boolean assignHouse(AssignHouseParam param) {
        //保存到租户和房屋的关系表
        LiveHouse liveHouse = new LiveHouse();
        liveHouse.setHouseId(param.getHouseId());
        liveHouse.setUserId(param.getUserId());
        liveHouse.setUseStatus("0");
        int insert = liveHouseMapper.insert(liveHouse);
        //更改房屋的使用状态
        HouseList house = new HouseList();
        house.setHouseId(param.getHouseId());
        house.setStatus("1");
        int updateById = houseListMapper.updateById(house);
        return insert == updateById;

    }

    /**
     * 分配车位保存
     *
     * @param livePark
     * @return
     */
    @Override
    @Transactional
    public boolean assignSavePark(@Valid LivePark livePark) {
        //1.把数据存储到租户和车位的关系表里面
        livePark.setLiveStatue("0");
        int insert = liveParkMapper.insert(livePark);
        //2.把车位表的状态改为已使用
        ParkList parkList = new ParkList();
        parkList.setParkId(livePark.getParkId());
        parkList.setParkStatus("1");
        int updateById = parkListMapper.updateById(parkList);
        return insert == updateById;
    }

    /**
     * 退房
     *
     * @param param
     */
    @Override
    @Transactional
    public boolean returnHouse(AssignHouseParam param) {
        //更新租户和房屋关系表状态为解绑；
        LiveHouse liveHouse = new LiveHouse();
        liveHouse.setUseStatus("1");
        QueryWrapper<LiveHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(LiveHouse::getHouseId, param.getHouseId())
                .eq(LiveHouse::getUserId, param.getUserId())
                .eq(LiveHouse::getUseStatus, "0");
        int updateLiveHouseStatus = liveHouseMapper.update(liveHouse, queryWrapper);
        //更新房屋表的使用状态为未使用；
        HouseList houseList = new HouseList();
        houseList.setStatus("0");
        QueryWrapper<HouseList> query = new QueryWrapper<>();
        query.lambda().eq(HouseList::getHouseId, param.getHouseId());
        int updateHouseListStatus = houseListMapper.update(houseList, query);
        return updateLiveHouseStatus == updateHouseListStatus;
    }

    /**
     * 退车位
     *
     * @param livePark
     */
    @Override
    @Transactional
    public boolean returnPark(LivePark livePark) {
        //2.更新租户和车位的关系为解绑；
        QueryWrapper<LivePark> query = new QueryWrapper<>();
        query.lambda().eq(LivePark::getParkId, livePark.getParkId())
                .eq(LivePark::getUserId, livePark.getUserId())
                .eq(LivePark::getLiveStatue, "0");
        LivePark Livepark = new LivePark();
        Livepark.setLiveStatue("1");
        int updateLiveParkStatus = liveParkMapper.update(Livepark, query);
        // 3.更新车位的使用状态为未使用；
        ParkList parkList = new ParkList();
        parkList.setParkStatus("0");
        parkList.setParkId(livePark.getParkId());
        int updateParkListStatus = parkListMapper.updateById(parkList);
        return updateLiveParkStatus == updateParkListStatus;
    }

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     */
    @Override
    public LiveUser loadUser(String username) {
        //认证
        QueryWrapper<LiveUser> query = new QueryWrapper<>();
        query.lambda().eq(LiveUser::getUsername,username);
        return this.baseMapper.selectOne(query);
    }
}
