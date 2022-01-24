package com.cy.liveManagement.live_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.liveManagement.live_user.entity.LiveUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-20 12:07:31
 */
public interface LiveUserMapper extends BaseMapper<LiveUser> {
    /**
     * 查询业主列表
     * @param page
     * @param userName
     * @param phone
     * @return
     */
    IPage<LiveUser> getLiveUserList(IPage<LiveUser> page, @Param("loginName") String userName, @Param("phone") String phone);

    /**
     * 查询业主数据
     * @param userId
     * @return
     */
    LiveUser getUser(@Param("userId") Long userId);
}
