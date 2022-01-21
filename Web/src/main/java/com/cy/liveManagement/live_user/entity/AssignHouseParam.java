package com.cy.liveManagement.live_user.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 分配房屋参数类
 * @date 2022-01-21 00:15:57
 */
@Data
public class AssignHouseParam implements Serializable {
    /**
     * 用户Id
     */
    @NotNull
    private Long userId;

    /**
     * 房屋Id
     */
    @NotNull
    private Long houseId;
}
