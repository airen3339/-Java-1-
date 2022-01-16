package com.cy.systemManagement.menu.entity;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 生成树工具
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 生成树工具
 * @date 2022-01-06 18:18:32
 */
public class MakeMenuTree {
    public static List<Menu> makeTree(List<Menu> menuList, Long pid) {
        List<Menu> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId().equals(pid))
                .forEach(dom -> {
                    Menu menu = new Menu();
                    BeanUtils.copyProperties(dom, menu);
                    List<Menu> menus = makeTree(menuList, dom.getMenuId());
                    menu.setChildren(menus);
                    list.add(menu);
                });
        return list;
    }
}
