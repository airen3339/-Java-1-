package com.cy.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.role.entity.Role;
import com.cy.role.entity.RoleParam;
import com.cy.role.service.RoleService;
import com.cy.utils.ResultUtils;
import com.cy.utils.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 角色管理控制器
 * @date 2021-12-22 16:29:22
 */
@RestController
@RequestMapping(value = "/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * @param param
     * @return com.cy.utils.ResultVo
     * @description: 查询员工角色
     * @date: 2021/12/22 16:43
     */

    @GetMapping("/list")
    public ResultVo list(RoleParam param) {
        IPage<Role> list = roleService.list(param);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * @param role
     * @return com.cy.utils.ResultVo
     * @description: 新增角色
     * @date: 2021/12/22 16:47
     */

    @PostMapping
    public ResultVo addRole(@RequestBody Role role) {
        boolean saveFlag = roleService.save(role);
        if (saveFlag) {
            return ResultUtils.success("新增角色成功");
        }
        return ResultUtils.success("新增角色失败");
    }

    /**
     * @param role
     * @return com.cy.utils.ResultVo
     * @description: 编辑角色
     * @date: 2021/12/22 16:49
     */

    @PutMapping
    public ResultVo editRole(@RequestBody Role role) {
        boolean editFlag = roleService.save(role);
        if (editFlag) {
            return ResultUtils.success("编辑角色成功");
        }
        return ResultUtils.success("编辑角色失败");
    }

    @DeleteMapping("/{roleId}")
    public ResultVo deleteRole(@PathVariable Long roleId){
        boolean removeFlag = roleService.removeById(roleId);
        if (removeFlag) {
            return ResultUtils.success("删除角色成功");
        }
        return ResultUtils.success("删除角色成功");
    }
}
