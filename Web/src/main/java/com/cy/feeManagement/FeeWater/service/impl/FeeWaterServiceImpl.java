package com.cy.feeManagement.FeeWater.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.feeManagement.FeeWater.entity.FeeWater;
import com.cy.feeManagement.FeeWater.entity.FeeWaterParam;
import com.cy.feeManagement.FeeWater.mapper.FeeWaterMapper;
import com.cy.feeManagement.FeeWater.service.FeeWaterService;
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
public class FeeWaterServiceImpl extends ServiceImpl<FeeWaterMapper, FeeWater> implements FeeWaterService {
    @Resource
    private LiveHouseMapper liveHouseMapper;
    @Resource
    private FeeWaterMapper feeWaterMapper;
    /**
     * 新增水费
     *
     * @param feeWater
     */
    @Override
    public int saveFeePower(FeeWater feeWater) {
        int flag = -1;
        //根据房屋id查询正在使用该房间用户
        QueryWrapper<LiveHouse> liveHouseQueryWrapper = new QueryWrapper<>();
        liveHouseQueryWrapper.lambda().eq(LiveHouse::getHouseId,feeWater.getHouseId())
                .eq(LiveHouse::getUseStatus,"0");
        LiveHouse house = liveHouseMapper.selectOne(liveHouseQueryWrapper);
        if (house != null) {
            QueryWrapper<FeeWater> feePowerQueryWrapper = new QueryWrapper<>();
            feePowerQueryWrapper.lambda().eq(FeeWater::getPayWaterMonth,feeWater.getPayWaterMonth()).eq(FeeWater::getHouseId,feeWater.getHouseId());
            FeeWater power = feeWaterMapper.selectOne(feePowerQueryWrapper);
            if (power == null) {
                //把查询出来的用户id设置到水费实体里面
                feeWater.setUserId(house.getUserId());
                //保存电费到数据库
                flag = this.baseMapper.insert(feeWater);
                return flag;
            }else {
                return flag = 2;
            }
        }
        return flag;
    }

    /**
     * 编辑水费
     *
     * @param feeWater
     */
    @Override
    public boolean updateFeePower(FeeWater feeWater) {
        //根据房屋id查询正在使用该房间用户
        QueryWrapper<LiveHouse> query = new QueryWrapper<>();
        query.lambda().eq(LiveHouse::getHouseId,feeWater.getHouseId())
                .eq(LiveHouse::getUseStatus,"0");
        LiveHouse house = liveHouseMapper.selectOne(query);
        if (house != null) {
            //把查询出来的用户id设置到电费实体里面
            feeWater.setUserId(house.getUserId());
            //更新电费到数据库
            int updateById = this.baseMapper.updateById(feeWater);
            return updateById == 1;
        }
        return false;
    }



    /**
     * 查询列表
     *
     * @param feeWaterParam
     * @return
     */
    @Override
    public IPage<FeeWater> getFeeWaterList(FeeWaterParam feeWaterParam) {
        //构造分页对象
        IPage<FeeWater> page = new Page<>();
        page.setCurrent(feeWaterParam.getCurrentPage());
        page.setSize(feeWaterParam.getPageSize());
        return this.baseMapper.getFeeWaterList(page,feeWaterParam.getUserName(),feeWaterParam.getHouseNum());
    }
}
