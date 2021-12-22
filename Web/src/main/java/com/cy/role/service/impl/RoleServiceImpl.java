package com.cy.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.role.entity.Role;
import com.cy.role.entity.RoleParam;
import com.cy.role.mapper.RoleMapper;
import com.cy.role.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2021-12-22 16:27:37
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public IPage<Role> list(RoleParam roleParam) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(roleParam.getRoleName())){
            queryWrapper.lambda().like(Role::getRoleName,roleParam.getRoleName());
        }

        IPage<Role> page = new Page<>();
        page.setCurrent(roleParam.getCurrentPage());
        page.setSize(roleParam.getPageSize());

        return this.baseMapper.selectPage(page,queryWrapper);
    }
}
