package com.cy.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.ResultMessage;
import com.cy.user.entity.User;
import com.cy.user.entity.UserParam;
import com.cy.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
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
     * @param user
     * @return com.cy.utils.ResultVo
     * @description: 添加员工
     * @date 2021/12/20 19:14
     */

    @PostMapping
    public ResultMessage addUser(@RequestBody User user) {
        //判断登录名是否存在
        if (StringUtils.isNotEmpty(user.getLoginName())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getLoginName,user.getLoginName());
            User one = userService.getOne(wrapper);
            if (one != null){
                return ResultMessage.fail(500,"登录名已存在");
            }
        }

        // 如果密码存在则进行MD5加密
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        boolean saveState = userService.save(user);
        if (saveState) {
            return ResultMessage.success("新增员工成功");
        }
        return ResultMessage.success("新增员工失败");
    }

    /**
     * @param user
     * @return com.cy.utils.ResultVo
     * @description: 编辑员工
     * @date 2021/12/20 19:22
     */

    @PutMapping
    public ResultMessage editUser(@RequestBody User user) {
        //判断登录名是否存在
        if (StringUtils.isNotEmpty(user.getLoginName())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getLoginName,user.getLoginName());
            User one = userService.getOne(wrapper);
            if (one != null && !one.getUserId().equals(user.getUserId())){
                return ResultMessage.fail(500,"登录名已存在");
            }
        }

        // 如果密码存在则进行MD5加密
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }

        boolean updateState = userService.updateById(user);
        if (updateState) {
            return ResultMessage.success("编辑员工成功");
        }
        return ResultMessage.success("编辑员工失败");
    }

    /**
     * @param userId
     * @return com.cy.utils.ResultVo
     * @description
     * @date 2021/12/20 20:01
     */

    @DeleteMapping("/{userId}")
    public ResultMessage deleteUser(@PathVariable("userId") long userId) {
        boolean removeState = userService.removeById(userId);
        if (removeState) {
            return ResultMessage.success("删除员工成功");
        }
        return ResultMessage.success("删除员工失败");
    }

    /***
     * @description: 员工列表查询
     * @param param
     * @return com.cy.utils.ResultVo
     * @date 2021/12/20 21:34
     */

    @GetMapping("/list")
    public ResultMessage listUsers(@Valid UserParam param , BindingResult result) {

        IPage<User> list = userService.list(param);
        list.getRecords().forEach(item -> item.setPassword(""));
        return ResultMessage.success("查询成功").add("list",list);
    }
}
