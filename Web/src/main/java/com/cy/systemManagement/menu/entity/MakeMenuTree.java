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
    /**
     * 生成菜单树
     * @param menuList
     * @param pid
     * @return
     */
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

    /**
     * 成功动态路由
     * @param menuList
     * @param pid
     * @return
     */
    public static List<RouterVO> makeRouter(List<Menu> menuList, Long pid){
        List<RouterVO> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId().equals(pid))
                .forEach(item -> {
                    RouterVO router = new RouterVO();
                    router.setName(item.getName());
                    router.setPath(item.getPath());
                    if(item.getParentId() == 0L){
                        router.setComponent("Layout");
                        router.setAlwaysShow(true);
                    }else{
                        router.setComponent(item.getUrl());
                        router.setAlwaysShow(false);
                    }
                    //设置meta
                    router.setMeta(router.new Meta(
                            item.getMenuLabel(),
                            item.getIcon(),
                            item.getMenuCode().split(",")
                    ));
                    //设置children,每一项的下级
                    List<RouterVO> children = makeRouter(menuList, item.getMenuId());
                    router.setChildren(children);
                    list.add(router);
                });
        return list;
    }
}
