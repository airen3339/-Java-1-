package com.cy.repairManagement.userRepair.controller;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-23 19:43:43
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.CommonResult;
import com.cy.repairManagement.userRepair.entity.UserRepair;
import com.cy.repairManagement.userRepair.entity.UserRepairParam;
import com.cy.repairManagement.userRepair.service.UserRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 维修管理模块
 */
@RestController
@RequestMapping("/api/userRepair")
public class UserRepairController {
    @Autowired
    private UserRepairService userRepairService;


    /**
     * 我的报修列表
     * @param userRepairParam
     * @return
     */
    @GetMapping("/myList")
    public CommonResult<IPage<UserRepair>> getMyList(@Valid UserRepairParam userRepairParam){
        //构造查询条件
        QueryWrapper<UserRepair> query = new QueryWrapper<>();
        query.lambda().eq(UserRepair::getUserId,userRepairParam.getUserId())
                .like(UserRepair::getRepairContent,userRepairParam.getRepairContent())
                .orderByDesc(UserRepair::getCommitTime);
        //构造分页对象
        IPage<UserRepair> page = new Page<>();
        page.setSize(userRepairParam.getPageSize());
        page.setCurrent(userRepairParam.getCurrentPage());
        IPage<UserRepair> list = userRepairService.page(page, query);
        return CommonResult.success("报修列表查询成功",list);
    }


    /**
     * 报修列表(物业管理部人员)
     * @param userRepairParam
     * @return
     */
    @GetMapping("/list")
    public CommonResult<IPage<UserRepair>> getList(@Valid UserRepairParam userRepairParam){
        //构造查询条件
        QueryWrapper<UserRepair> query = new QueryWrapper<>();
        query.lambda().like(UserRepair::getRepairContent,userRepairParam.getRepairContent())
                .orderByDesc(UserRepair::getCommitTime);
        //构造分页对象
        IPage<UserRepair> page = new Page<>();
        page.setSize(userRepairParam.getPageSize());
        page.setCurrent(userRepairParam.getCurrentPage());
        IPage<UserRepair> list = userRepairService.page(page, query);
        return CommonResult.success("报修列表查询成功",list);
    }


    /**
     * 新增
     * @param userRepair
     * @return
     */
    @PreAuthorize("hasAuthority('sys:myRepair:add')")
    @PostMapping
    public CommonResult<String> add(@RequestBody @Valid UserRepair userRepair){
        userRepair.setCommitTime(new Date());
        userRepair.setStatus("0");
        boolean saveStatus = userRepairService.save(userRepair);
        if(saveStatus){
            return CommonResult.success("报修成功!");
        }
        return CommonResult.error("报修失败!");
    }


    /**
     * 编辑
     * @param userRepair
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:myRepair:edit','sys:repairList:do')")
    @PutMapping
    public CommonResult<String> edit(@RequestBody @Valid UserRepair userRepair){
        QueryWrapper<UserRepair> userRepairQueryWrapper = new QueryWrapper<>();
        userRepairQueryWrapper.lambda().eq(UserRepair::getRepairId,userRepair.getRepairId());
        UserRepair one = userRepairService.getOne(userRepairQueryWrapper);
        if ("1".equals(one.getStatus())){
            return CommonResult.error("该维修已处理");
        }
        boolean editStatus = userRepairService.updateById(userRepair);
        if(editStatus){
            return CommonResult.success("编辑成功!");
        }
        return CommonResult.error("编辑失败!");
    }


    /**
     * 删除
     * @param repairId
     * @return
     */
    @PreAuthorize("hasAuthority('sys:myRepair:delete')")
    @DeleteMapping("/{repairId}")
    public CommonResult<String> delete(@PathVariable("repairId") @Valid Long repairId){
        boolean deleteStatus = userRepairService.removeById(repairId);
        if(deleteStatus){
            return CommonResult.success("删除成功!");
        }
        return CommonResult.error("删除失败!");
    }
}
