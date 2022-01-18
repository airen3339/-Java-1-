package com.cy.homeManagement.house_unit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.homeManagement.house_unit.entity.HouseUnit;
import com.cy.homeManagement.house_unit.entity.HouseUnitParam;
import com.cy.homeManagement.house_unit.serveie.HouseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping
    public CommonResult<String> editHouseUnit(@RequestBody @Valid  HouseUnit houseUnit){
        boolean editStatus = houseUnitService.updateById(houseUnit);
        if(editStatus){
            return CommonResult.success("编辑单元成功!");
        }
        return CommonResult.error("编辑单元失败!");
    }

    /**
     * 删除单元
     * @param unitId
     * @return
     */
    @DeleteMapping("/{unitId}")
    public CommonResult<String> deleteHouseUnit(@PathVariable("unitId") @Valid  Long unitId){
        boolean deleteStatus = houseUnitService.removeById(unitId);
        if(deleteStatus){
            return CommonResult.success("删除单元成功!");
        }
        return CommonResult.error("删除单元失败!");
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
