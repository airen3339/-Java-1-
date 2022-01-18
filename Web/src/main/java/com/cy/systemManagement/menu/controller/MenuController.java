package com.cy.systemManagement.menu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.CommonResult;
import com.cy.systemManagement.menu.entity.Menu;
import com.cy.systemManagement.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限菜单信息管理
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 权限菜单信息管理
 * @date 2022-01-06 17:55:03
 */
@RestController
@RequestMapping("api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @PostMapping
    public CommonResult<Menu> addMenu(@RequestBody Menu menu){
        int i = menuService.menuIsExist(menu.getParentId(), menu.getMenuLabel());
        if (i == 1){
            return CommonResult.error("新增类型已存在");
        }else {
            boolean saveFlag = menuService.save(menu);
            if (saveFlag) {
                return CommonResult.success("新增菜单成功");
            }
            return CommonResult.error("新增菜单失败");
        }
    }

    /**
     * 编辑菜单
     * @param menu
     * @return
     */
    @PutMapping
    public CommonResult<Menu> editMenu(@RequestBody Menu menu){
        boolean editFlag = menuService.updateById(menu);
        if (editFlag) {
            return CommonResult.success("编辑菜单成功");
        }
        return CommonResult.error("编辑菜单失败");
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @DeleteMapping("/{menuId}")
    public CommonResult<Menu> deleteMenu(@PathVariable String menuId){
        // 如果有下级则不能直接删除
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getParentId,menuId);
        List<Menu> list = menuService.list(queryWrapper);
        if (list.size() > 0){
            return CommonResult.error("该菜单存在下级，不能直接删除");
        }

        boolean editFlag = menuService.removeById(menuId);
        if (editFlag) {
            return CommonResult.success("删除菜单成功");
        }
        return CommonResult.error("删除菜单失败");
    }

    /**
     * 查询树形表格数据
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<Menu>> list(){
        List<Menu> list = menuService.getList();
        return CommonResult.success("查询成功",list);
    }


    /**
     * 获取上级菜单
     * @return
     */
    @GetMapping("/parent")
    public CommonResult<List<Menu>> getParent(){
        List<Menu> parentList = menuService.getParentList();
        return CommonResult.success("上级菜单数据查询成功",parentList);
    }
}
