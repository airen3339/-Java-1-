package com.cy.complaintManagement.userComplaint.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.CommonResult;
import com.cy.complaintManagement.userComplaint.entity.UserComplaint;
import com.cy.complaintManagement.userComplaint.entity.UserComplaintParam;
import com.cy.complaintManagement.userComplaint.service.UserComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-22 23:01:30
 */
@RestController
@RequestMapping("/api/userComplaint")
public class UserComplaintController {
    @Autowired
    private UserComplaintService userComplaintService;


    /**
     * 投诉列表
     * @param param
     * @return
     */
    @GetMapping("/list")
    public CommonResult<IPage<UserComplaint>> getList(@Valid UserComplaintParam param){
        //构造查询条件
        QueryWrapper<UserComplaint> query = new QueryWrapper<>();
        query.lambda().like(UserComplaint::getTitle,param.getTitle())
                .like(UserComplaint::getComplaintContent,param.getComplaintContent());
        //构造分页对象
        IPage<UserComplaint> page = new Page<>();
        page.setCurrent(param.getCurrentPage());
        page.setSize(param.getPageSize());
        IPage<UserComplaint> list = userComplaintService.page(page, query);
        return CommonResult.success("投诉列表查询成功",list);
    }


    /**
     * 新增投诉
     * @param userComplaint
     * @return
     */
    @PreAuthorize("hasAuthority('sys:myUserComplaint:add')")
    @PostMapping
    public CommonResult<String> add(@RequestBody @Valid UserComplaint userComplaint){
        //设置投诉状态
        userComplaint.setStatus("0");
        //设置投诉时间
        userComplaint.setCreateTime(new Date());
        //入库保存
        boolean saveStatus = userComplaintService.save(userComplaint);
        if(saveStatus){
            return CommonResult.success("投诉成功!");
        }
        return CommonResult.error("投诉失败!");
    }


    /**
     * 编辑投诉
     * @param userComplaint
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:myUserComplaint:edit','sys:myUserComplaint:do')")
    @PutMapping
    public CommonResult<String> edit(@RequestBody @Valid UserComplaint userComplaint){
        UserComplaint userComplaintServiceById = userComplaintService.getById(userComplaint.getComplaintId());
        if ("1".equals(userComplaintServiceById.getStatus())){
            return CommonResult.error("该投诉已经处理");
        }
        //编辑保存
        boolean updateStatus = userComplaintService.updateById(userComplaint);
        if(updateStatus){
            return CommonResult.success("编辑成功!");
        }
        return CommonResult.error("编辑失败!");
    }

    /**
     * 删除
     * @param complaintId
     * @return
     */
    @PreAuthorize("hasAuthority('sys:myUserComplaint:delete')")
    @DeleteMapping("/{complaintId}")
    public CommonResult<String> delete(@PathVariable("complaintId") @Valid Long complaintId){
        boolean deleteStatus = userComplaintService.removeById(complaintId);
        if(deleteStatus){
            return CommonResult.success("删除成功!");
        }
        return CommonResult.error("删除失败!");
    }

    /**
     * 我的投诉
     * @param param
     * @return
     */
    @GetMapping("/myList")
    public CommonResult<IPage<UserComplaint>> getMyList(UserComplaintParam param){
        //构造查询条件
        QueryWrapper<UserComplaint> query = new QueryWrapper<>();
        query.lambda().like(UserComplaint::getTitle,param.getTitle())
                .like(UserComplaint::getComplaintContent,param.getComplaintContent())
                .eq(UserComplaint::getUserId,param.getUserId());
        //构造分页对象
        IPage<UserComplaint> page = new Page<>();
        page.setCurrent(param.getCurrentPage());
        page.setSize(param.getPageSize());
        IPage<UserComplaint> list = userComplaintService.page(page, query);
        return CommonResult.success("我的投诉列表查询成功",list);
    }
}
