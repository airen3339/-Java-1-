package com.cy.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.user.entity.User;
import com.cy.user.entity.UserParam;
import com.cy.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 员工信息管理
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 员工管理控制器
 * @date 2021-12-20 18:44:07
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 添加员工
     * @param user
     * @return com.cy.utils.ResultVo
     * @description: 添加员工
     * @date 2021/12/20 19:14
     */

    @PostMapping
    public CommonResult<User> addUser(@RequestBody @Valid User user) {
        QueryWrapper<User> wrapper = null;
        //判断登录名是否存在
        if (StringUtils.isNotEmpty(user.getLoginName())){
            wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getLoginName,user.getLoginName());
            User one = userService.getOne(wrapper);
            if (one != null){
                return CommonResult.error(500,"登录名已存在");
            }
        }

        //判断身份证是否存在
        if (StringUtils.isNotEmpty(user.getIdCard())){
            wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getIdCard,user.getIdCard());
            User one = userService.getOne(wrapper);
            if (one != null){
                return CommonResult.error(500,"身份证号已存在");
            }
        }

        // 如果密码存在则进行MD5加密
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        boolean saveState = userService.save(user);
        if (saveState) {
            return CommonResult.success("新增员工成功");
        }
        return CommonResult.error("新增员工失败");
    }

    /**
     * 编辑员工
     * @param user
     * @return com.cy.utils.ResultVo
     * @description: 编辑员工
     * @date 2021/12/20 19:22
     */
    @PutMapping
    public CommonResult<User> editUser(@RequestBody @Valid User user) {
        //判断登录名是否存在
        if (StringUtils.isNotEmpty(user.getLoginName())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getLoginName,user.getLoginName());
            User one = userService.getOne(wrapper);
            if (one != null && !one.getUserId().equals(user.getUserId())){
                return CommonResult.error(500,"登录名已存在");
            }
        }

        // 如果密码存在则进行MD5加密
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }

        boolean updateState = userService.updateById(user);
        if (updateState) {
            return CommonResult.success("编辑员工成功");
        }
        return CommonResult.success("编辑员工失败");
    }

    /**
     * 删除员工
     * @param userId
     * @return com.cy.utils.ResultVo
     * @description
     * @date 2021/12/20 20:01
     */

    @DeleteMapping("/{userId}")
    public CommonResult<User> deleteUser(@PathVariable("userId") long userId) {
        boolean removeState = userService.removeById(userId);
        if (removeState) {
            return CommonResult.success("删除员工成功");
        }
        return CommonResult.error("删除员工失败");
    }

    /***
     * 员工列表查询
     * @description: 员工列表查询
     * @param param
     * @return com.cy.utils.ResultVo
     * @date 2021/12/20 21:34
     */

    @GetMapping("/list")
    public CommonResult<IPage<User>> listUsers(@Valid UserParam param) {

        IPage<User> list = userService.list(param);
        list.getRecords().forEach(item -> item.setPassword("*********"));
        return CommonResult.success("员工列表数据获取成功",list);
    }
}
