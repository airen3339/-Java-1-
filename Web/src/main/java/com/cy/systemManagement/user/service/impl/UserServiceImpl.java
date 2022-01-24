package com.cy.systemManagement.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.systemManagement.user.entity.User;
import com.cy.systemManagement.user.entity.UserParam;
import com.cy.systemManagement.user.mapper.UserMapper;
import com.cy.systemManagement.user.service.UserService;
import com.cy.systemManagement.user_role.entity.UserRole;
import com.cy.systemManagement.user_role.mapper.UserRoleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2021-12-20 18:35:28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public IPage<User> list(UserParam param) {
        //构建分页对象
        IPage<User> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getCurrentPage());
        //构造前端查询条件
        QueryWrapper<User> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(param.getPhone())){
            query.lambda().like(User::getPhone, param.getPhone());
        }
        if(StringUtils.isNotEmpty(param.getLoginName())){
            query.lambda().like(User::getLoginName, param.getLoginName());
        }
        return this.page(page,query);
    }

    /**
     * 根据用户id查询角色id
     *
     * @param userRole
     * @return
     */
    @Override
    public UserRole getRoleByUserId(UserRole userRole) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId,userRole.getUserId());
        return userRoleMapper.selectOne(queryWrapper);
    }

    /**
     * 分配角色保存
     *
     * @param userRole
     */
    @Override
    @Transactional
    public void saveRole(UserRole userRole) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId,userRole.getUserId());
        userRoleMapper.delete(queryWrapper);
        userRoleMapper.insert(userRole);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User loadUser(String username) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getUsername, username);
        return this.baseMapper.selectOne(query);
    }
}
