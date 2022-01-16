package com.cy.systemManagement.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 权限菜单
 * @date 2022-01-06 15:21:10
 */

@Data
@TableName("sys_menu")
public class Menu implements Serializable {
    /**
     * 主键
     * @ignore
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;

    /**
     * 上级菜单id
     */
    private Long parentId;

    /**
     * 上级菜单名称
     */
    private String parentName;


    /**
     * 菜单名称
     */
    private String menuLabel;

    /**
     * 权限字段
     */
    private String menuCode;

    /**
     * 路由名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String url;

    /**
     * 菜单类型 0：目录 1：菜单 2：按钮
     */
    private String type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 序号
     */
    private Integer orderNum;

    /**
     * 权限列表
     *
     */
    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();

    /**
     * 用于判断图标的增减
     */
    @TableField(exist = false)
    private Boolean open;
}
