package com.cy.homeManagement.house_list.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.homeManagement.house_list.entity.HouseList;
import com.cy.homeManagement.house_list.entity.ListParam;
import com.cy.homeManagement.house_list.service.HouseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 21:54:16
 */
@RestController
@RequestMapping(value = "api/houseList")
public class HouseListController {
    @Autowired
    private HouseListService houseListService;
    /**
     * 房屋查询列表
     * @param param
     * @return
     */
    @RequestMapping("/list")
    public CommonResult<IPage<HouseList>> getList(@Valid ListParam param){
        IPage<HouseList> list = houseListService.getList(param);
        return CommonResult.success("房屋列表查询成功",list);
    }

    /**
     * 新增房屋
     * @param houseList
     * @return
     */
    @PostMapping
    public CommonResult<String> addHouse(@RequestBody @Valid HouseList houseList){
        int saveState = houseListService.saveHouseList(houseList);
        if(saveState == 1){
            return CommonResult.success("新增房屋成功!");
        }else if (saveState == 2){
            return CommonResult.error("房屋编号已存在");
        }else {
            return CommonResult.error("新增房屋失败!");
        }
    }

    /**
     * 编辑房屋
     * @param houseList
     * @return
     */
    @PutMapping
    public CommonResult<String> editHouse(@RequestBody @Valid HouseList houseList){
        boolean editState = houseListService.updateById(houseList);
        if(editState){
            return CommonResult.success("编辑房屋成功!");
        }
        return CommonResult.error("编辑房屋失败!");
    }

    /**
     * 删除房屋
     * @param houseId
     * @return
     */
    @DeleteMapping("/{houseId}")
    public CommonResult<String> deleteHouse(@PathVariable("houseId") @Valid String houseId){
        boolean deleteState = houseListService.removeById(houseId);
        if(deleteState){
            return CommonResult.success("删除房屋成功!");
        }
        return CommonResult.error("删除房屋失败!");
    }

    /**
     * 根据单元id查询房屋列表
     * @param unitId
     * @return
     */
    @GetMapping("/getHouseListByUnitId")
    public CommonResult<List<HouseList>> getHouseListByUnitId(long unitId){
        QueryWrapper<HouseList> houseListQueryWrapper = new QueryWrapper<>();
        houseListQueryWrapper.lambda().eq(HouseList::getUnitId,unitId);
        List<HouseList> list = houseListService.list(houseListQueryWrapper);
        return CommonResult.success("房屋列表查询成功",list);
    }
}
