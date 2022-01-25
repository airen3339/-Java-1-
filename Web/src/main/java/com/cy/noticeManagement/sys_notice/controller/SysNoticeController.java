package com.cy.noticeManagement.sys_notice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.CommonResult;
import com.cy.noticeManagement.sys_notice.entity.SysNotice;
import com.cy.noticeManagement.sys_notice.entity.SysNoticeParam;
import com.cy.noticeManagement.sys_notice.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-24 01:11:04
 */
@RestController
@RequestMapping("/api/sysNotice")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;

    /**
     * 公告查询列表
     * @param param
     * @return
     */
    @GetMapping("/list")
    public CommonResult<IPage<SysNotice>> getList(SysNoticeParam param){
        //构造查询条件
        QueryWrapper<SysNotice> query = new QueryWrapper<>();
        query.lambda().like(SysNotice::getTitle,param.getTitle())
                .orderByDesc(SysNotice::getCreateTime);
        //构造分页对象
        IPage<SysNotice> page = new Page<>();
        page.setCurrent(param.getCurrentPage());
        page.setSize(param.getPageSize());
        IPage<SysNotice> list = sysNoticeService.page(page, query);
        return CommonResult.success("公告列表查询成功",list);
    }


    /**
     * 新增
     * @param sysNotice
     * @return
     */
    @PreAuthorize("hasAuthority('sys:noticeList:add')")
    @PostMapping
    public CommonResult<String> add(@RequestBody SysNotice sysNotice){
        sysNotice.setCreateTime(new Date());
        boolean saveStatus = sysNoticeService.save(sysNotice);
        if(saveStatus){
            return CommonResult.success("新增成功!");
        }
        return CommonResult.error("新增失败!");
    }


    /**
     * 编辑
     * @param sysNotice
     * @return
     */
    @PreAuthorize("hasAuthority('sys:noticeList:edit')")
    @PutMapping
    public CommonResult<String> edit(@RequestBody SysNotice sysNotice){
        boolean editStatus = sysNoticeService.updateById(sysNotice);
        if(editStatus){
            return CommonResult.success("编辑成功!");
        }
        return CommonResult.error("编辑失败!");
    }


    /**
     * 删除
     * @param noticeId
     * @return
     */
    @PreAuthorize("hasAuthority('sys:noticeList:delete')")
    @DeleteMapping("/{noticeId}")
    public CommonResult<String> delete(@PathVariable("noticeId") Long noticeId){
        boolean deleteStatus = sysNoticeService.removeById(noticeId);
        if(deleteStatus){
            return CommonResult.success("删除成功!");
        }
        return CommonResult.error("删除失败!");
    }
}
