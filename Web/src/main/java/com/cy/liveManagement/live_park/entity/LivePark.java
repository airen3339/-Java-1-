package com.cy.liveManagement.live_park.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 业主车位关系表
 * @date 2022-01-20 11:10:30
 */
@Data
@TableName("live_park")
public class LivePark implements Serializable {
    /**
     * 租户车位id
     */
    @TableId(type = IdType.AUTO)
    private Long liveParkId;

    /**
     * 业主id
     */
    @NotNull
    private Long userId;

    /**
     * 车位id
     */
    @NotNull
    private Long parkId;

    /**
     * 使用状态
     */
    private String liveStatue;
}
