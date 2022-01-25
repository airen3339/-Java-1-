package com.cy.systemManagement.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.liveManagement.live_role.entity.LiveRole;
import com.cy.liveManagement.live_role.mapper.LiveRoleMapper;
import com.cy.systemManagement.role.entity.*;
import com.cy.systemManagement.role.service.RoleService;
import com.cy.systemManagement.user_role.entity.UserRole;
import com.cy.systemManagement.user_role.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色信息管理
 *
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
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private LiveRoleMapper liveRoleMapper;

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/getList")
    public CommonResult<List<Role>> getList(){
        List<Role> list = roleService.list();
        return CommonResult.success("角色列表查询成功",list);
    }

    /**
     * 分配权限保存
     *
     * @param param
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:role:assignMenu')")
    @PostMapping("/saveAssignRole")
    public CommonResult<String> saveAssignRole(@RequestBody @Valid RolePermissionParam param) {
        roleService.saveAssignRole(param.getRoleId(), param.getIdList());
        return CommonResult.success("分配权限成功!");
    }

    /**
     * 权限树回显查询
     *
     * @param roleParam
     * @return
     */
    @GetMapping("/getAssignPermissionTree")
    public CommonResult<RolePermissionVo> getAssignMenuTree(@Valid RoleAssignParam roleParam) {
        RolePermissionVo assignMenuTree = roleService.getAssignTree(roleParam);
        return CommonResult.success("权限树查询成功", assignMenuTree);
    }

    /**
     * 查询员工角色
     *
     * @param param
     * @return com.cy.utils.ResultVo
     * @description: 查询员工角色
     * @date: 2021/12/22 16:43
     */

    @GetMapping("/list")
    public CommonResult<IPage<Role>> list(@Valid RoleParam param) {
        IPage<Role> list = roleService.list(param);
        return CommonResult.success("角色列表查询成功", list);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return com.cy.utils.ResultVo
     * @description: 新增角色
     * @date: 2021/12/22 16:47
     */
    @PreAuthorize("hasAnyAuthority('sys:role:add')")
    @PostMapping
    public CommonResult<Role> addRole(@RequestBody @Valid Role role) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.lambda().eq(Role::getRoleName, role.getRoleName());
        Role one = roleService.getOne(roleQueryWrapper);
        if (one != null) {
            return CommonResult.error("角色已存在");
        } else {
            boolean saveFlag = roleService.save(role);
            if (saveFlag) {
                return CommonResult.success("新增角色成功");
            }
            return CommonResult.error("新增角色失败");
        }
    }

    /**
     * 编辑角色
     *
     * @param role
     * @return com.cy.utils.ResultVo
     * @description: 编辑角色
     * @date: 2021/12/22 16:49
     */
    @PreAuthorize("hasAnyAuthority('sys:role:edit')")
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
     *
     * @param roleId
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:role:delete')")
    @DeleteMapping("/{roleId}")
    public CommonResult<Role> deleteRole(@PathVariable @Valid Long roleId) {
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(UserRole::getRoleId,roleId);
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQueryWrapper);
        if (userRoles.size() > 0){
            return CommonResult.error("角色已分配，不能删除");
        }

        QueryWrapper<LiveRole> liveUserQueryWrapper = new QueryWrapper<>();
        liveUserQueryWrapper.lambda().eq(LiveRole::getRoleId,roleId);
        List<LiveRole> liveUserRoles = liveRoleMapper.selectList(liveUserQueryWrapper);
        if (liveUserRoles.size() > 0){
            return CommonResult.error("角色已分配，不能删除");
        }

        boolean removeFlag = roleService.removeById(roleId);
        if (removeFlag) {
            return CommonResult.success("删除角色成功");
        }
        return CommonResult.error("删除角色失败");
    }
}
