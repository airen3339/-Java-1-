package com.cy.liveManagement.live_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.CommonResult;
import com.cy.feeManagement.FeePark.entity.FeePark;
import com.cy.feeManagement.FeePark.service.FeeParkService;
import com.cy.feeManagement.FeePower.entity.FeePower;
import com.cy.feeManagement.FeePower.service.FeePowerService;
import com.cy.feeManagement.FeeWater.entity.FeeWater;
import com.cy.feeManagement.FeeWater.service.FeeWaterService;
import com.cy.liveManagement.live_house.entity.LiveHouse;
import com.cy.liveManagement.live_house.mapper.LiveHouseMapper;
import com.cy.liveManagement.live_park.entity.LivePark;
import com.cy.liveManagement.live_park.mapper.LiveParkMapper;
import com.cy.liveManagement.live_user.entity.AssignHouseParam;
import com.cy.liveManagement.live_user.entity.LiveUser;
import com.cy.liveManagement.live_user.entity.LiveUserParam;
import com.cy.liveManagement.live_user.service.LiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-20 12:09:54
 */
@RestController
@RequestMapping(value = "api/liveUser")
public class LiveUserController {
    @Autowired
    private LiveUserService liveUserService;
    @Autowired
    private FeePowerService feePowerService;
    @Autowired
    private FeeWaterService feeWaterService;
    @Autowired
    private FeeParkService feeParkService;
    @Resource
    private LiveHouseMapper liveHouseMapper;
    @Resource
    private LiveParkMapper liveParkMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 新增业主
     */
    @PreAuthorize("hasAuthority('sys:liveUser:add')")
    @PostMapping
    public CommonResult<String> add(@RequestBody @Valid LiveUser liveUser) {

        QueryWrapper<LiveUser> liveUserQueryWrapper = new QueryWrapper<>();
        liveUserQueryWrapper.lambda()
                .eq(LiveUser::getPhone, liveUser.getPhone());
        LiveUser one = liveUserService.getOne(liveUserQueryWrapper);
        if (one != null) {
            return CommonResult.error("手机号已被使用，请重试");
        }


        QueryWrapper<LiveUser> liveUserQueryWrapper1 = new QueryWrapper<>();
        liveUserQueryWrapper1.lambda()
                .eq(LiveUser::getUsername, liveUser.getUsername());
        LiveUser one1 = liveUserService.getOne(liveUserQueryWrapper1);
        if (one1 != null) {
            return CommonResult.error("登录名已被使用，请重试");
        }

        //用户名需要加密
        liveUser.setPassword(passwordEncoder.encode(liveUser.getPassword()));
        boolean saveStatus = liveUserService.saveLiveUser(liveUser);
        if (saveStatus) {
            return CommonResult.success("新增业主成功!");
        } else {
            return CommonResult.error("新增业主失败!");
        }


    }

    /**
     * 业主列表查询
     */
    @GetMapping("/list")
    public CommonResult<IPage<LiveUser>> getLiveUserList(@Valid LiveUserParam liveUserParam) {
        //构造分页对象
        IPage<LiveUser> page = new Page<>();
        page.setSize(liveUserParam.getPageSize());
        page.setCurrent(liveUserParam.getCurrentPage());
        IPage<LiveUser> list = liveUserService.getLiveUserList(page, liveUserParam.getLoginName(), liveUserParam.getPhone());
        return CommonResult.success("业主列表查询成功", list);
    }

    /**
     * 编辑业主
     */
    @PreAuthorize("hasAuthority('sys:liveUser:edit')")
    @PutMapping
    public CommonResult<String> edit(@RequestBody @Valid LiveUser liveUser) {
        //查询登录名是否被占用
        QueryWrapper<LiveUser> query = new QueryWrapper<>();
        query.lambda().eq(LiveUser::getUsername, liveUser.getUsername());
        LiveUser one = liveUserService.getOne(query);
        if (one != null && !one.getUserId().equals(liveUser.getUserId())) {
            return CommonResult.error("登录名已存在!");
        }

        QueryWrapper<LiveUser> liveUserQueryWrapper = new QueryWrapper<>();
        liveUserQueryWrapper.lambda()
                .eq(LiveUser::getPhone, liveUser.getPhone());
        LiveUser one1 = liveUserService.getOne(liveUserQueryWrapper);
        if (one1 != null && !one1.getUserId().equals(liveUser.getUserId())) {
            return CommonResult.error("手机号已被使用，请重试");
        }

        boolean editStatus = liveUserService.editLiveUser(liveUser);
        if (editStatus) {
            return CommonResult.success("编辑成功!");
        } else {
            return CommonResult.error("编辑失败");
        }
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @PreAuthorize("hasAuthority('sys:liveUser:delete')")
    @DeleteMapping("/{userId}")
    public CommonResult<String> deleteUser(@PathVariable("userId") @Valid Long userId) {
        QueryWrapper<LiveHouse> liveHouseQueryWrapper = new QueryWrapper<>();
        liveHouseQueryWrapper.lambda().eq(LiveHouse::getUserId, userId);
        List<LiveHouse> liveHouseList = liveHouseMapper.selectList(liveHouseQueryWrapper);

        QueryWrapper<LivePark> liveParkQueryWrapper = new QueryWrapper<>();
        liveParkQueryWrapper.lambda().eq(LivePark::getUserId, userId);
        List<LivePark> liveParks = liveParkMapper.selectList(liveParkQueryWrapper);

        for (LiveHouse liveHouse : liveHouseList) {
            if ("0".equals(liveHouse.getUseStatus())) {
                return CommonResult.error("请退房之后在操作");
            }
        }
        for (LivePark livePark : liveParks) {
            if ("0".equals(livePark.getLiveStatue())) {
                return CommonResult.error("请退车位之后在操作");
            }
        }
        boolean removeStatus = liveUserService.removeById(userId);
        if (removeStatus) {
            return CommonResult.success("删除成功!");
        } else {
            return CommonResult.error("删除失败");
        }
    }

    /**
     * 单个业主数据查询
     */
    @GetMapping("/getUserById")
    public CommonResult<LiveUser> getUserById(@Valid long userId) {
        LiveUser user = liveUserService.getUser(userId);
        return CommonResult.success("业主查询成功", user);
    }

    /**
     * 分配房屋保存
     */
    @PreAuthorize("hasAuthority('sys:liveUser:assignHome')")
    @PostMapping("/assignSave")
    public CommonResult<String> assignSave(@RequestBody @Valid AssignHouseParam param) {
        boolean assignStatus = liveUserService.assignHouse(param);
        if (assignStatus) {
            return CommonResult.success("分配房屋成功!");
        } else {
            return CommonResult.error("分配房屋失败!");
        }
    }

    /**
     * 分配车位保存
     */
    @PreAuthorize("hasAuthority('sys:liveUser:assignCar')")
    @PostMapping("/assignParkSave")
    public CommonResult<String> assignParkSave(@RequestBody LivePark livePark) {
        boolean saveParkStatus = liveUserService.assignSavePark(livePark);
        if (saveParkStatus) {
            return CommonResult.success("分配车位成功!");
        } else {
            return CommonResult.error("分配车位失败!");
        }
    }


    /**
     * 退房
     *
     * @param param
     * @return
     */
    @PreAuthorize("hasAuthority('sys:liveUser:returnHome')")
    @PostMapping("/returnHouse")
    public CommonResult<String> returnHouse(@RequestBody @Valid AssignHouseParam param) {
        //1.查询电费、水费是否交清
        //构造查询条件
        QueryWrapper<FeeWater> queryWater = new QueryWrapper<>();
        queryWater.lambda().eq(FeeWater::getHouseId, param.getHouseId())
                .eq(FeeWater::getUserId, param.getUserId())
                .eq(FeeWater::getPayWaterStatus, "0");
        List<FeeWater> list = feeWaterService.list(queryWater);
        if (list != null && list.size() > 0) {
            return CommonResult.error("请缴水费之后再退房!");
        }
        //查询电费
        QueryWrapper<FeePower> queryPower = new QueryWrapper<>();
        queryPower.lambda().eq(FeePower::getHouseId, param.getHouseId())
                .eq(FeePower::getUserId, param.getUserId())
                .eq(FeePower::getPayPowerStatus, "0");
        List<FeePower> list1 = feePowerService.list(queryPower);
        if (list1 != null && list1.size() > 0) {
            return CommonResult.error("请缴电费之后再退房!");
        }
        boolean returnHouseStatus = liveUserService.returnHouse(param);
        if (returnHouseStatus) {
            return CommonResult.success("退房成功!");
        } else {
            return CommonResult.error("退房失败!");
        }
    }


    /**
     * 退车位
     *
     * @param livePark
     * @return
     */
    @PreAuthorize("hasAuthority('sys:liveUser:returnCar')")
    @PostMapping("/returnPark")
    public CommonResult<String> returnPark(@RequestBody @Valid LivePark livePark) {
        // 1.查询车位费是否已经交清；
        QueryWrapper<FeePark> query = new QueryWrapper<>();
        query.lambda().eq(FeePark::getParkId, livePark.getParkId())
                .eq(FeePark::getUserId, livePark.getUserId())
                .eq(FeePark::getPayParkStatus, "0");
        List<FeePark> list = feeParkService.list(query);
        if (list != null && list.size() > 0) {
            return CommonResult.error("请缴清停车费后再退车位!");
        }
        boolean returnParkStatus = liveUserService.returnPark(livePark);
        if (returnParkStatus) {
            return CommonResult.success("退车位成功!");
        } else {
            return CommonResult.error("退车位失败!");
        }

    }
}
