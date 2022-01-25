package com.cy.systemManagement.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.systemManagement.menu.entity.Menu;

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


    /**
     * 根据用户id查询权限列表
     * @param userId
     * @return
     */
    List<Menu> getMenuListByUserId(Long userId);


    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    List<Menu> getMenuListByRoleId(Long roleId);

    /**
     * @param parentId
     * @param menuLabel
     * @return
     */
    int menuIsExist(Long parentId,String menuLabel );


    /**
     * 根据业主的id查询菜单
     * @param userId
     * @return
     */
    List<Menu> getMenuByUserIdForLiveUser(Long userId);
}
