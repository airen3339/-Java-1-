package com.cy.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 权限菜单接口
 * @date 2022-01-06 15:23:58
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
