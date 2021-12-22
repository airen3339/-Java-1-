package com.cy.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.user.entity.User;
import com.cy.user.entity.UserParm;
import com.cy.user.service.UserService;
import com.cy.utils.ResultUtils;
import com.cy.utils.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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
    public ResultVo addUser(@RequestBody User user) {
        //判断登录名是否存在
        if (StringUtils.isNotEmpty(user.getLoginName())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getLoginName,user.getLoginName());
            User one = userService.getOne(wrapper);
            if (one != null){
                return ResultUtils.error("登录名已存在",500);
            }
        }

        // 如果密码存在则进行MD5加密
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        boolean saveState = userService.save(user);
        if (saveState) {
            return ResultUtils.success("新增员工成功");
        }
        return ResultUtils.success("新增员工失败");
    }

    /**
     * @param user
     * @return com.cy.utils.ResultVo
     * @description: 编辑员工
     * @date 2021/12/20 19:22
     */

    @PutMapping
    public ResultVo editUser(@RequestBody User user) {
        //判断登录名是否存在
        if (StringUtils.isNotEmpty(user.getLoginName())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getLoginName,user.getLoginName());
            User one = userService.getOne(wrapper);
            if (one != null && !one.getUserId().equals(user.getUserId())){
                return ResultUtils.error("登录名已存在",500);
            }
        }

        // 如果密码存在则进行MD5加密
        if (StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }

        boolean updateState = userService.updateById(user);
        if (updateState) {
            return ResultUtils.success("编辑员工成功");
        }
        return ResultUtils.success("编辑新增员工失败");
    }

    /**
     * @param userId
     * @return com.cy.utils.ResultVo
     * @description
     * @date 2021/12/20 20:01
     */

    @DeleteMapping("/{userId}")
    public ResultVo deleteUser(@PathVariable("userId") long userId) {
        boolean removeState = userService.removeById(userId);
        if (removeState) {
            return ResultUtils.success("编辑员工成功");
        }
        return ResultUtils.success("编辑新增员工失败");
    }

    /***
     * @description: 员工列表查询
     * @param param
     * @return com.cy.utils.ResultVo
     * @date 2021/12/20 21:34
     */

    @GetMapping("/list")
    public ResultVo listUsers(UserParm param) {
        IPage<User> list = userService.list(param);
        list.getRecords().forEach(item -> item.setPassword(""));
        return ResultUtils.success("查询成功", list);
    }
}
