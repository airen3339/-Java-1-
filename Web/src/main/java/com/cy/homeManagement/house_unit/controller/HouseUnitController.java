package com.cy.homeManagement.house_unit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.homeManagement.house_list.entity.HouseList;
import com.cy.homeManagement.house_list.mapper.HouseListMapper;
import com.cy.homeManagement.house_unit.entity.HouseUnit;
import com.cy.homeManagement.house_unit.entity.HouseUnitParam;
import com.cy.homeManagement.house_unit.serveie.HouseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 17:51:29
 */
@RestController
@RequestMapping("/api/houseUnit")
public class HouseUnitController {
    @Autowired
    private HouseUnitService houseUnitService;
    @Resource
    private HouseListMapper houseListMapper;

    /**
     * 房屋单元列表
     */
    @GetMapping("/list")
    public CommonResult<IPage<HouseUnit>> getList(@Valid HouseUnitParam param){
        IPage<HouseUnit> list = houseUnitService.getList(param);
        return CommonResult.success("房屋单元列表查询成功",list);
    }

    /**
     * 新增单元
     * @param houseUnit
     * @return
     */
    @PreAuthorize("hasAuthority('sys:houseUnit:add')")
    @PostMapping
    public CommonResult<String> addHouseUnit(@RequestBody @Valid HouseUnit houseUnit){

        QueryWrapper<HouseUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HouseUnit::getUnitName,houseUnit.getUnitName());
        HouseUnit one = houseUnitService.getOne(queryWrapper);
        if (one != null){
            return CommonResult.error("单元已存在!");
        }else {
            boolean saveStatus = houseUnitService.save(houseUnit);
            if(saveStatus){
                return CommonResult.success("新增单元成功!");
            }
            return CommonResult.error("新增单元失败!");
        }


    }
    /**
     * 编辑单元
     * @param houseUnit
     * @return
     */
    @PreAuthorize("hasAuthority('sys:houseUnit:edit')")
    @PutMapping
    public CommonResult<String> editHouseUnit(@RequestBody @Valid  HouseUnit houseUnit){
        QueryWrapper<HouseUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HouseUnit::getBuildId,houseUnit.getBuildId());
        List<HouseUnit> list = houseUnitService.list(queryWrapper);
        for (HouseUnit houseUnit1:list) {
            if (houseUnit1.getUnitName().equals(houseUnit.getUnitName())){
                return CommonResult.error("单元已存在!");
            }
        }

            boolean editStatus = houseUnitService.updateById(houseUnit);
            if (editStatus) {
                return CommonResult.success("编辑单元成功!");
            }
            return CommonResult.error("编辑单元失败!");

    }

    /**
     * 删除单元
     * @param unitId
     * @return
     */
    @PreAuthorize("hasAuthority('sys:houseUnit:delete')")
    @DeleteMapping("/{unitId}")
    public CommonResult<String> deleteHouseUnit(@PathVariable("unitId") @Valid  Long unitId){
        QueryWrapper<HouseList> houseListQueryWrapper = new QueryWrapper<>();
        houseListQueryWrapper.lambda().eq(HouseList::getHouseId,unitId);
        List<HouseList> liveHouses = houseListMapper.selectList(houseListQueryWrapper);
        if (liveHouses.size() > 0){
            return CommonResult.error("该单元已有房屋，不能删除");
        }else {
            boolean deleteStatus = houseUnitService.removeById(unitId);
            if (deleteStatus) {
                return CommonResult.success("删除单元成功!");
            }
            return CommonResult.error("删除单元失败!");
        }
    }

    /**
     * 根据栋数id查询单元列表
     */
    @GetMapping("/getUnitListByBuildId")
    public CommonResult<List<HouseUnit>> getUnitListByBuildId(HouseUnit houseUnit){
        QueryWrapper<HouseUnit> query = new QueryWrapper<>();
        query.lambda().eq(HouseUnit::getBuildId,houseUnit.getBuildId());
        List<HouseUnit> list = houseUnitService.list(query);
        return CommonResult.success("单元列表查询成功",list);
    }
}
