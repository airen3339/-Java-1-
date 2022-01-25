package com.cy.parkManagement.park_list.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.parkManagement.park_list.entity.ParkList;
import com.cy.parkManagement.park_list.entity.ParkListParam;
import com.cy.parkManagement.park_list.service.ParkListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-19 13:36:07
 */
@RestController
@RequestMapping(value = "api/parkList")
public class ParkListController {
    @Autowired
    private ParkListService parkListService;

    /**
     * 车位查询列表
     */
    @GetMapping("/listNoPage")
    public CommonResult<List<ParkList>> getList(){
        List<ParkList> list = parkListService.list();
        return CommonResult.success("车位列表查询成功",list);
    }

    /**
     * 车位查询列表
     */
    @GetMapping("/list")
    public CommonResult<IPage<ParkList>> getListNoPage(@Valid ParkListParam parkListParam){
        IPage<ParkList> list = parkListService.getParkList(parkListParam);
        return CommonResult.success("车位列表查询成功",list);
    }

    /**
     * 新增车位
     */
    @PreAuthorize("hasAuthority('sys:parkList:add')")
    @PostMapping
    public CommonResult<String> add(@RequestBody @Valid  ParkList parkList){
        QueryWrapper<ParkList> parkListQueryWrapper = new QueryWrapper<>();
        parkListQueryWrapper.lambda().eq(ParkList::getParkName,parkList.getParkName());
        ParkList one = parkListService.getOne(parkListQueryWrapper);
        if (one != null) {
            return CommonResult.error("车位已经存在!");
        }else {
            boolean saveStatus = parkListService.save(parkList);
            if (saveStatus) {
                return CommonResult.success("新增车位成功!");
            }
            return CommonResult.error("新增车位失败!");
        }
    }

    /**
     * 编辑车位
     */
    @PreAuthorize("hasAuthority('sys:parkList:edit')")
    @PutMapping
    public CommonResult<String> edit(@RequestBody @Valid ParkList parkList){
        QueryWrapper<ParkList> parkListQueryWrapper = new QueryWrapper<>();
        parkListQueryWrapper.lambda().eq(ParkList::getParkName,parkList.getParkName());
        ParkList one = parkListService.getOne(parkListQueryWrapper);
        if (one != null && !one.getParkId().equals(parkList.getParkId())) {
            return CommonResult.error("车位已经存在!");
        }else {
            boolean editStatus = parkListService.updateById(parkList);
            if (editStatus) {
                return CommonResult.success("编辑车位成功!");
            }
            return CommonResult.error("编辑车位失败!");
        }
    }

    /**
     * 删除车位
     */
    @PreAuthorize("hasAuthority('sys:parkList:delete')")
    @DeleteMapping("/{parkId}")
    public CommonResult<String> delete(@PathVariable("parkId") @Valid Long parkId){
        QueryWrapper<ParkList> parkListQueryWrapper = new QueryWrapper<>();
        parkListQueryWrapper.lambda().eq(ParkList::getParkId,parkId);
        ParkList one = parkListService.getOne(parkListQueryWrapper);
        if("1".equals(one.getParkStatus())){
            return CommonResult.error("该车位正在使用，无法删除!");
        }
        boolean deleteStatus = parkListService.removeById(parkId);
        if(deleteStatus){
            return CommonResult.success("删除车位成功!");
        }
        return CommonResult.error("删除车位失败!");
    }
}
