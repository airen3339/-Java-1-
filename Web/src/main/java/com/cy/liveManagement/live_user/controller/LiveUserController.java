package com.cy.liveManagement.live_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.CommonResult;
import com.cy.liveManagement.live_park.entity.LivePark;
import com.cy.liveManagement.live_user.entity.AssignHouseParam;
import com.cy.liveManagement.live_user.entity.LiveUser;
import com.cy.liveManagement.live_user.entity.LiveUserParam;
import com.cy.liveManagement.live_user.service.LiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * 新增业主
     */
    @PostMapping
    public CommonResult<String> add(@RequestBody @Valid LiveUser liveUser){

        QueryWrapper<LiveUser> liveUserQueryWrapper = new QueryWrapper<>();
        liveUserQueryWrapper.lambda()
                .eq(LiveUser::getPhone,liveUser.getPhone())
                .or()
                .eq(LiveUser::getLoginName,liveUser.getLoginName());
        LiveUser one = liveUserService.getOne(liveUserQueryWrapper);
        if (one != null) {
         return CommonResult.error("手机号/登录名已被使用，请重试");
        }else {
            //用户名需要加密
            liveUser.setPassword(DigestUtils.md5DigestAsHex(liveUser.getPassword().getBytes()));
            boolean saveStatus = liveUserService.saveLiveUser(liveUser);
            if (saveStatus) {
                return CommonResult.success("新增业主成功!");
            }else {
                return CommonResult.error("新增业主失败!");
            }

        }
    }

    /**
     * 业主列表查询
     */
    @GetMapping("/list")
    public CommonResult<IPage<LiveUser>> getLiveUserList(@Valid LiveUserParam liveUserParam){
        //构造分页对象
        IPage<LiveUser> page = new Page<>();
        page.setSize(liveUserParam.getPageSize());
        page.setCurrent(liveUserParam.getCurrentPage());
        IPage<LiveUser> list = liveUserService.getLiveUserList(page, liveUserParam.getUserName(), liveUserParam.getPhone());
        return CommonResult.success("业主列表查询成功",list);
    }

    /**
     * 编辑业主
     */
    @PutMapping
    public CommonResult<String> edit(@RequestBody @Valid LiveUser liveUser){
        //查询登录名是否被占用
        QueryWrapper<LiveUser> query = new QueryWrapper<>();
        query.lambda().eq(LiveUser::getLoginName,liveUser.getLoginName());
        LiveUser one = liveUserService.getOne(query);
        if(one != null && !one.getUserId().equals(liveUser.getUserId())){
            return CommonResult.error("登录名已存在!");
        }
        boolean editStatus = liveUserService.editLiveUser(liveUser);
        if (editStatus) {
            return CommonResult.success("编辑成功!");
        }else {
            return CommonResult.error("编辑失败");
        }
    }

    /**
     * 单个业主数据查询
     */
    @GetMapping("/getUserById")
    public CommonResult<LiveUser> getUserById(@Valid long userId){
        LiveUser user = liveUserService.getUser(userId);
        return CommonResult.success("业主查询成功",user);
    }

    /**
     * 分配房屋保存
     */
    @PostMapping("/assignSave")
    public CommonResult<String> assignSave(@RequestBody @Valid AssignHouseParam param){
        boolean assignStatus = liveUserService.assignHouse(param);
        if (assignStatus) {
            return CommonResult.success("分配房屋成功!");
        }else {
            return CommonResult.error("分配房屋失败!");
        }
    }

    /**
     * 分配车位保存
     */
    @PostMapping("/assignParkSave")
    public CommonResult<String> assignParkSave(@RequestBody LivePark livePark){
        boolean saveParkStatus = liveUserService.assignSavePark(livePark);
        if (saveParkStatus) {
            return CommonResult.success("分配车位成功!");
        }else {
            return CommonResult.error("分配车位失败!");
        }
    }
}
