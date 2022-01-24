package com.cy.feeManagement.FeePark.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.feeManagement.FeePark.entity.FeePark;
import com.cy.feeManagement.FeePark.entity.FeeParkParam;
import com.cy.feeManagement.FeePark.service.FeeParkService;
import com.cy.liveManagement.live_park.entity.LivePark;
import com.cy.liveManagement.live_park.mapper.LiveParkMapper;
import com.cy.valid.feePay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-22 10:47:42
 */
@RestController
@RequestMapping("/api/feePark")
public class FeeParkController {
    @Autowired
    private FeeParkService feeParkService;
    @Resource
    private LiveParkMapper liveParkMapper;


    /**
     * 新增停车费
     * @param feePark
     * @return
     */
    @PostMapping
    public CommonResult<String> add(@RequestBody FeePark feePark){
        //1.查询当前正在使用车位的租户
        //构造查询条件
        QueryWrapper<LivePark> query = new QueryWrapper<>();
        query.lambda().eq(LivePark::getParkId,feePark.getParkId())
                .eq(LivePark::getLiveStatue,"0");
        LivePark livePark = liveParkMapper.selectOne(query);
        if(livePark == null){
            return CommonResult.error("该车位暂无人员使用!");
        }
        feePark.setUserId(livePark.getUserId());
        //2.入库
        boolean saveStatus = feeParkService.save(feePark);
        if(saveStatus){
            return CommonResult.success("新增停车费成功!");
        }
        return CommonResult.error("新增停车费失败!");
    }


    /**
     * 编辑停车费
     * @param feePark
     * @return
     */
    @PutMapping
    public CommonResult<String> edit(@RequestBody FeePark feePark){
        QueryWrapper<FeePark> liveParkQueryWrapper = new QueryWrapper<>();
        liveParkQueryWrapper.lambda().eq(FeePark::getParkFeeId,feePark.getParkFeeId());
        FeePark one = feeParkService.getOne(liveParkQueryWrapper);
        if ("1".equals(one.getPayParkStatus())){
            return CommonResult.error("已缴费，无法编辑");
        }
        //1.查询当前正在使用车位的租户
        //构造查询条件
        QueryWrapper<LivePark> query = new QueryWrapper<>();
        query.lambda().eq(LivePark::getParkId,feePark.getParkId())
                .eq(LivePark::getLiveStatue,"0");
        LivePark livePark = liveParkMapper.selectOne(query);
        if(livePark == null){
            return CommonResult.error("该车位暂无人员使用!");
        }
        feePark.setUserId(livePark.getUserId());
        //2.入库
        boolean editStatus = feeParkService.updateById(feePark);
        if(editStatus){
            return CommonResult.success("编辑停车费成功!");
        }
        return CommonResult.error("编辑停车费失败!");
    }


    /**
     * 删除停车费
     * @param parkFeeId
     * @return
     */
    @DeleteMapping("/{parkFeeId}")
    public CommonResult<String> delete(@PathVariable("parkFeeId") @Valid  Long parkFeeId){
        QueryWrapper<FeePark> query = new QueryWrapper<>();
        query.lambda().eq(FeePark::getPayParkStatus,"1")
                .eq(FeePark::getParkFeeId,parkFeeId);
        FeePark one = feeParkService.getOne(query);
        if(one != null){
            return CommonResult.error("已缴费，不能删除该数据!");
        }
        boolean deleteStatus = feeParkService.removeById(parkFeeId);
        if(deleteStatus){
            return CommonResult.success("删除停车费成功!");
        }
        return CommonResult.error("删除停车费失败!");
    }

    /**
     * 水费列表查询
     */
    @GetMapping("/list")
    public CommonResult<IPage<FeePark>> getFeeParkList(@Valid FeeParkParam param){
        IPage<FeePark> list = feeParkService.getFeeParkList(param);
        return CommonResult.success("停车费列表查询成功",list);
    }

    /**
     * 缴费
     */
    @PostMapping("/payPark")
    public CommonResult<String> payPark(@RequestBody @Validated(value = {feePay.class}) FeePark feePark){
        feePark.setPayParkTime((new Date()));
        QueryWrapper<FeePark> feeParkQueryWrapper = new QueryWrapper<>();
        feeParkQueryWrapper.lambda().isNull(FeePark::getPayParkTime);
        FeePark one = feeParkService.getOne(feeParkQueryWrapper);
        if (one != null) {
            boolean updateByIdStatus = feeParkService.updateById(feePark);
            if(updateByIdStatus){
                return CommonResult.success("缴费成功!");
            }
            return CommonResult.error("缴费失败!");
        }else {
            return CommonResult.error("已缴费，无需重复缴费!");
        }
    }
}
