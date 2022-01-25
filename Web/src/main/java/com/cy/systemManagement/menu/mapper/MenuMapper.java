package com.cy.systemManagement.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.systemManagement.menu.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 权限菜单接口
 * @date 2022-01-06 15:23:58
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询权限列表
     * @param userId
     * @return
     */
    List<Menu> getMenuListByUserId(@Param("userId") Long userId);

    /**
     * 根据业主id查询权限列表
     * @param userId
     * @return
     */
    List<Menu> getMenuByUserIdForLiveUser(@Param("userId") Long userId);

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    List<Menu> getMenuListByRoleId(@Param("roleId") Long roleId);


}
