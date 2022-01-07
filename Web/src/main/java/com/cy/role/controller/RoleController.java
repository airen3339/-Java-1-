package com.cy.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.role.entity.Role;
import com.cy.role.entity.RoleParam;
import com.cy.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色信息管理
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 角色管理控制器
 * @date 2021-12-22 16:29:22
 */
@RestController
@RequestMapping(value = "/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 查询员工角色
     * @param param
     * @return com.cy.utils.ResultVo
     * @description: 查询员工角色
     * @date: 2021/12/22 16:43
     */

    @GetMapping("/list")
    public CommonResult<IPage<Role>> list(@Valid RoleParam param) {
        IPage<Role> list = roleService.list(param);
        return CommonResult.success("查询成功", list);
    }

    /**
     * 新增角色
     * @param role
     * @return com.cy.utils.ResultVo
     * @description: 新增角色
     * @date: 2021/12/22 16:47
     */

    @PostMapping
    public CommonResult<Role> addRole(@RequestBody @Valid Role role) {
        boolean saveFlag = roleService.save(role);
        if (saveFlag) {
            return CommonResult.success("新增角色成功");
        }
        return CommonResult.error("新增角色失败");
    }

    /**
     * 编辑角色
     * @param role
     * @return com.cy.utils.ResultVo
     * @description: 编辑角色
     * @date: 2021/12/22 16:49
     */

    @PutMapping
    public CommonResult<Role> editRole(@RequestBody @Valid Role role) {
        boolean editFlag = roleService.updateById(role);
        if (editFlag) {
            return CommonResult.success("编辑角色成功");
        }
        return CommonResult.error("编辑角色失败");
    }

    /**
     * 根据角色Id删除角色
     * @param roleId
     * @return
     */
    @DeleteMapping("/{roleId}")
    public CommonResult<Role> deleteRole(@PathVariable @Valid Long roleId){
        boolean removeFlag = roleService.removeById(roleId);
        if (removeFlag) {
            return CommonResult.success("删除角色成功");
        }
        return CommonResult.error("删除角色失败");
    }
}
