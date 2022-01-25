package com.cy.feeManagement.FeePower.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.CommonResult;
import com.cy.feeManagement.FeePower.entity.FeePower;
import com.cy.feeManagement.FeePower.entity.FeePowerParam;
import com.cy.feeManagement.FeePower.service.FeePowerService;
import com.cy.valid.feeAddOrEdit;
import com.cy.valid.feePay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-21 15:28:17
 */
@RestController
@RequestMapping("/api/feePower")
public class FeePowerController {
    @Autowired
    private FeePowerService feePowerService;


    /**
     * 我的电费
     * @param feePowerParam
     * @return
     */
    @GetMapping("/getMyPowerFee")
    public CommonResult<IPage<FeePower>> getMyParkFee(FeePowerParam feePowerParam){
        //构造分页对象
        IPage<FeePower> page = new Page<>();
        page.setCurrent(feePowerParam.getCurrentPage());
        page.setSize(feePowerParam.getPageSize());
        //查询条件
        QueryWrapper<FeePower> query = new QueryWrapper<>();
        query.lambda().eq(FeePower::getUserId,feePowerParam.getUserId());
        IPage<FeePower> list = feePowerService.page(page, query);
        return CommonResult.success("我的电费查询成功",list);
    }

    /**
     * 新增电费
     * @param feePower
     * @return
     */
    @PreAuthorize("hasAuthority('sys:feePower:add')")
    @PostMapping
    public CommonResult<String> add(@RequestBody @Validated(value = {feeAddOrEdit.class}) FeePower feePower){
        int saveFeePowerStatus = feePowerService.saveFeePower(feePower);
        if (saveFeePowerStatus == 1){
            return CommonResult.success("新增电费成功!");
        }else if (saveFeePowerStatus == -1) {
            return CommonResult.error("该房屋尚未被使用，不能添加电费!");
        }else if (saveFeePowerStatus == 2){
            return CommonResult.error("该房屋本月已添加电费，不能重复添加!");
        }else {
            return CommonResult.error("新增电费失败!");
        }

    }


    /**
     * 编辑电费
     * @param feePower
     * @return
     */
    @PreAuthorize("hasAuthority('sys:feePower:edit')")
    @PutMapping
    public CommonResult<String> edit(@RequestBody @Validated(value = {feeAddOrEdit.class}) FeePower feePower){
        QueryWrapper<FeePower> liveParkQueryWrapper = new QueryWrapper<>();
        liveParkQueryWrapper.lambda().eq(FeePower::getPowerId,feePower.getPowerId());
        FeePower one = feePowerService.getOne(liveParkQueryWrapper);
        if ("1".equals(one.getPayPowerStatus())){
            return CommonResult.error("已缴费，无法编辑");
        }
        boolean updateFeePowerStatus = feePowerService.updateFeePower(feePower);
        if (updateFeePowerStatus) {
            return CommonResult.success("编辑电费成功!");
        }else {
            return CommonResult.error("编辑电费失败!");
        }
    }


    /**
     * 删除电费
     * @param powerId
     * @return
     */
    @PreAuthorize("hasAuthority('sys:feePower:delete')")
    @DeleteMapping("/{powerId}")
    public CommonResult<String> delete(@PathVariable("powerId") @Valid Long powerId){
        //如果已经缴费，就不能删除
        QueryWrapper<FeePower> query = new QueryWrapper<>();
        query.lambda().eq(FeePower::getPowerId,powerId).eq(FeePower::getPayPowerStatus,"1");
        FeePower one = feePowerService.getOne(query);
        if(one != null){
            return CommonResult.error("已缴费，不能删除!");
        }
        //删除操作
        boolean removeByIdStatus = feePowerService.removeById(powerId);
        if(removeByIdStatus){
            return CommonResult.success("删除电费成功!");
        }
        return CommonResult.error("删除电费失败!");
    }

    /**
     * 电费列表查询
     */
    @GetMapping("/list")
    public CommonResult<IPage<FeePower>> getFeePowerList(@Valid FeePowerParam param){
        IPage<FeePower> list = feePowerService.getFeePowerList(param);
        return CommonResult.success("电费列表查询成功",list);
    }

    /**
     * 缴费
     */
    @PreAuthorize("hasAuthority('sys:feePower:pay')")
    @PostMapping("/payPower")
    public CommonResult<String> payPower(@RequestBody @Validated(value = {feePay.class}) FeePower feePower){
        feePower.setPayPowerTime((new Date()));
        QueryWrapper<FeePower> feePowerQueryWrapper = new QueryWrapper<>();
        feePowerQueryWrapper.lambda().isNull(FeePower::getPayPowerTime);
        FeePower one = feePowerService.getOne(feePowerQueryWrapper);
        if (one != null) {
            boolean updateByIdStatus = feePowerService.updateById(feePower);
            if(updateByIdStatus){
                return CommonResult.success("缴费成功!");
            }
            return CommonResult.error("缴费失败!");
        }else {
            return CommonResult.error("已缴费，无需重复缴费!");
        }
    }
}
