package com.cy.systemManagement.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.CommonResult;
import com.cy.config.jwt.JwtUtils;
import com.cy.liveManagement.live_user.entity.LiveUser;
import com.cy.systemManagement.user.entity.*;
import com.cy.systemManagement.user.service.UserService;
import com.cy.systemManagement.user_role.entity.UserRole;
import com.cy.valid.getRoleByUserId;
import com.cy.valid.saveRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 员工信息管理
 *
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
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户id查询角色id
     *
     * @param userRole
     * @return
     */
    @GetMapping("/getRoleByUserId")
    public CommonResult<UserRole> getRoleByUserId(@Validated({getRoleByUserId.class}) UserRole userRole) {
        UserRole userRole1 = userService.getRoleByUserId(userRole);
        return CommonResult.success("查询成功", userRole1);
    }


    /**
     * 分配角色保存
     *
     * @param userRole
     * @return
     */
    @PostMapping("/saveRole")
    public CommonResult<String> saveRole(@RequestBody @Validated({getRoleByUserId.class, saveRole.class}) UserRole userRole) {
        userService.saveRole(userRole);
        return CommonResult.success("分配角色成功!");
    }

    /**
     * 获取用户信息
     *
     * @param user
     * @return
     */
    @GetMapping("/getInfo")
    public CommonResult<UserInfo> getInfo(User user) {
        User service = userService.getById(user.getUserId());
        UserInfo userInfo = new UserInfo();
        userInfo.setId(service.getUserId());
        userInfo.setName(service.getUsername());
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return CommonResult.success("获取用户信息成功", userInfo);
    }

    /**
     * 登录
     *
     * @param param
     * @return
     */
    @PostMapping("/login")
    public CommonResult<LoginResult> login(@RequestBody LoginParam param) {
        if (StringUtils.isEmpty(param.getUsername()) || StringUtils.isEmpty(param.getPassword()) || StringUtils.isEmpty(param.getUserType())) {
            return CommonResult.error("用户名、密码或用户类型不能为空!");
        }
        String encode = passwordEncoder.encode(param.getPassword());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(param.getUsername() + ":" + param.getUserType(), param.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        //用户信息
        if (param.getUserType().equals("0")) { //业主
            LiveUser liveUser = (LiveUser) authenticate.getPrincipal();
            //生成token返回给前端
            String liveToken = jwtUtils.generateToken(liveUser.getUsername(), param.getUserType());
            //获取token过期的时间
            Long time = jwtUtils.getExpireTime(liveToken, jwtUtils.getSecret());
            LoginResult result = new LoginResult();
            result.setUserId(liveUser.getUserId());
            result.setToken(liveToken);
            result.setExpireTime(time);
            return CommonResult.success("登录成功", result);
        } else if (param.getUserType().equals("1")) { //物主
            User user = (User) authenticate.getPrincipal();
            //生成token返回给前端
            String token = jwtUtils.generateToken(user.getUsername(), param.getUserType());
            //获取token过期的时间
            Long time = jwtUtils.getExpireTime(token, jwtUtils.getSecret());
            LoginResult result = new LoginResult();
            result.setUserId(user.getUserId());
            result.setToken(token);
            result.setExpireTime(time);
            return CommonResult.success("登录成功", result);
        } else {
            return CommonResult.error("您选择的用户类型不存在!");
        }
    }


    //@PostMapping("/login")
    //public CommonResult<LoginResult> login(@RequestBody @Valid LoginParam loginParam){
    //    String password = DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes());
    //    QueryWrapper<User> loginParamQueryWrapper = new QueryWrapper<>();
    //    loginParamQueryWrapper.lambda().eq(User::getLoginName,loginParam.getUsername())
    //            .eq(User::getPassword,password);
    //
    //    User user = userService.getOne(loginParamQueryWrapper);
    //    if (user == null) {
    //        return CommonResult.error("用户名不存在或密码错误");
    //    }
    //    //如果用户存在，生成token返回给前端
    //    String token = jwtUtils.generateToken(user.getUsername());
    //    //获取生成的token的过期时间
    //    Long expireTime =  jwtUtils.getExpireTime(token,jwtUtils.getSecret());
    //    LoginResult loginResult = new LoginResult();
    //    loginResult.setUserId(user.getUserId());
    //    loginResult.setToken(token);
    //    loginResult.setExpireTime(expireTime);
    //    return CommonResult.success("登录成功",loginResult);
    //}

    /**
     * 添加员工
     *
     * @param user
     * @return com.cy.utils.ResultVo
     * @description: 添加员工
     * @date 2021/12/20 19:14
     */

    @PostMapping
    public CommonResult<User> addUser(@RequestBody @Valid User user) {
        QueryWrapper<User> wrapper = null;
        //判断登录名是否存在
        if (StringUtils.isNotEmpty(user.getUsername())) {
            wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getUsername, user.getUsername());
            User one = userService.getOne(wrapper);
            if (one != null) {
                return CommonResult.error(500, "登录账号已存在");
            }
        }

        //判断身份证是否存在
        if (StringUtils.isNotEmpty(user.getIdCard())) {
            wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getIdCard, user.getIdCard());
            User one = userService.getOne(wrapper);
            if (one != null) {
                return CommonResult.error(500, "身份证号已存在");
            }
        }

        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        boolean saveState = userService.save(user);
        if (saveState) {
            return CommonResult.success("新增员工成功");
        }
        return CommonResult.error("新增员工失败");
    }

    /**
     * 编辑员工
     *
     * @param user
     * @return com.cy.utils.ResultVo
     * @description: 编辑员工
     * @date 2021/12/20 19:22
     */
    @PutMapping
    public CommonResult<User> editUser(@RequestBody @Valid User user) {
        //判断登录名是否存在
        if (StringUtils.isNotEmpty(user.getUsername())) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(User::getUsername, user.getUsername());
            User one = userService.getOne(wrapper);
            if (one != null && !one.getUserId().equals(user.getUserId())) {
                return CommonResult.error(500, "登录账号已存在");
            }
        }

        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        boolean updateState = userService.updateById(user);
        if (updateState) {
            return CommonResult.success("编辑员工成功");
        }
        return CommonResult.success("编辑员工失败");
    }

    /**
     * 删除员工
     *
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
        return CommonResult.success("员工列表数据获取成功", list);
    }
}
