package com.cy.homeManagement.house_list.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.homeManagement.house_list.entity.HouseList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-17 21:52:12
 */
public interface HouseListMapper extends BaseMapper<HouseList> {
    /**
     * 房屋列表查询
     * @param page
     * @param buildName
     * @param unitName
     * @param houseNum
     * @param status
     * @return
     */
    IPage<HouseList> getList(IPage<HouseList> page, @Param("buildName") String buildName, @Param("unitName") String unitName, @Param("houseNum") String houseNum, @Param("status") String status);

    /**
     * 根据单元id查询房屋数据
     * @param unitId
     * @return
     */
    List<HouseList> getHouseListByUnitId(Long unitId);

    List<HouseList> editHouseList(@Param("unitId") Long unitId,@Param("buildId") Long buildId);
}
