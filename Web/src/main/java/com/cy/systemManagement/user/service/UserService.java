package com.cy.systemManagement.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.systemManagement.user.entity.User;
import com.cy.systemManagement.user.entity.UserParam;
import com.cy.systemManagement.user_role.entity.UserRole;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 员工业务层
 * @date 2021-12-20 18:33:49
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户列表
     * @param param
     * @return
     */
    IPage<User> list(UserParam param);


    /**
     * 根据用户id查询角色id
     * @param userRole
     * @return
     */
    UserRole getRoleByUserId(UserRole userRole);

    /**
     * 分配角色保存
     * @param userRole
     */
    void saveRole(UserRole userRole);


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User loadUser(String username);
}
