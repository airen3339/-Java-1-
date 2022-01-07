package com.cy.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.menu.entity.Menu;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2022-01-06 15:25:56
 */
public interface MenuService extends IService<Menu> {

    /**
     *  获取菜单列表树数据
     */
    List<Menu> getList();

    /**
     *  查询上级菜单
     */
    List<Menu> getParentList();
}
