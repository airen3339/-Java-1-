package com.cy.liveManagement.live_house.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 业主房屋关系表
 * @date 2022-01-20 11:16:23
 */
@Data
@TableName("live_house")
public class LiveHouse implements Serializable {

    /**
     * 业主房屋Id
     */
    @TableId(type = IdType.AUTO)
    private Long liveHouseId;

    /**
     * 业主id
     */
    private Long userId;

    /**
     * 房屋id
     */
    private Long houseId;

    /**
     * 使用状态 0:未使用 1：已使用
     */
    private String useStatus;
}
