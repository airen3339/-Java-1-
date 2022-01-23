package com.cy.feeManagement.FeePower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.feeManagement.FeePower.entity.FeePower;
import com.cy.feeManagement.FeePower.entity.FeePowerParam;
import com.cy.feeManagement.FeePower.mapper.FeePowerMapper;
import com.cy.feeManagement.FeePower.service.FeePowerService;
import com.cy.liveManagement.live_house.entity.LiveHouse;
import com.cy.liveManagement.live_house.mapper.LiveHouseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-21 14:16:56
 */
@Service
public class FeePowerServiceImpl extends ServiceImpl<FeePowerMapper, FeePower> implements FeePowerService {
    @Resource
    private LiveHouseMapper liveHouseMapper;
    @Resource
    private FeePowerMapper feePowerMapper;
    /**
     * 新增电费
     *
     * @param feePower
     */
    @Override
    public int saveFeePower(FeePower feePower) {
        int flag = -1;
        //根据房屋id查询正在使用该房间用户
        QueryWrapper<LiveHouse> liveHouseQueryWrapper = new QueryWrapper<>();
        liveHouseQueryWrapper.lambda().eq(LiveHouse::getHouseId,feePower.getHouseId())
                .eq(LiveHouse::getUseStatus,"0");
        LiveHouse house = liveHouseMapper.selectOne(liveHouseQueryWrapper);
        if (house != null) {
            QueryWrapper<FeePower> feePowerQueryWrapper = new QueryWrapper<>();
            feePowerQueryWrapper.lambda()
                    .eq(FeePower::getPayPowerMonth,feePower.getPayPowerMonth())
                    .eq(FeePower::getHouseId,feePower.getHouseId());
            FeePower power = feePowerMapper.selectOne(feePowerQueryWrapper);
            if (power == null) {
                //把查询出来的用户id设置到电费实体里面
                feePower.setUserId(house.getUserId());
                //保存电费到数据库
                flag = this.baseMapper.insert(feePower);
                return flag;
            }else {
                return flag = 2;
            }
        }
        return flag;
    }

    /**
     * 编辑电费
     *
     * @param feePower
     */
    @Override
    public boolean updateFeePower(FeePower feePower) {
        //根据房屋id查询正在使用该房间用户
        QueryWrapper<LiveHouse> query = new QueryWrapper<>();
        query.lambda().eq(LiveHouse::getHouseId,feePower.getHouseId())
                .eq(LiveHouse::getUseStatus,"0");
        LiveHouse house = liveHouseMapper.selectOne(query);
        if (house != null) {
            //把查询出来的用户id设置到电费实体里面
            feePower.setUserId(house.getUserId());
            //更新电费到数据库
            int updateById = this.baseMapper.updateById(feePower);
            return updateById == 1;
        }

        return false;
    }



    /**
     * 查询列表
     *
     * @param feePowerParam
     * @return
     */
    @Override
    public IPage<FeePower> getFeePowerList(FeePowerParam feePowerParam) {
        //构造分页对象
        IPage<FeePower> page = new Page<>();
        page.setCurrent(feePowerParam.getCurrentPage());
        page.setSize(feePowerParam.getPageSize());
        return this.baseMapper.getFeePowerList(page,feePowerParam.getUserName(),feePowerParam.getHouseNum());
    }
}
