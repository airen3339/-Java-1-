package com.cy.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.user.entity.User;
import com.cy.user.entity.UserParam;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 员工业务层
 * @date 2021-12-20 18:33:49
 */
public interface UserService extends IService<User> {
    //查询用户列表
    IPage<User> list(UserParam parm);
}
