package com.cy.feeManagement.FeePark.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.feeManagement.FeePark.entity.FeePark;
import com.cy.feeManagement.FeePark.entity.FeeParkParam;
import com.cy.feeManagement.FeePark.mapper.FeeParkMapper;
import com.cy.feeManagement.FeePark.service.FeeParkService;
import org.springframework.stereotype.Service;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-22 10:38:41
 */
@Service
public class FeeParkServiceImpl extends ServiceImpl<FeeParkMapper, FeePark> implements FeeParkService {
    /**
     * 查询列表
     *
     * @param feeParkParam
     * @return
     */
    @Override
    public IPage<FeePark> getFeeParkList(FeeParkParam feeParkParam) {
        IPage<FeePark> page = new Page<>();
        page.setSize(feeParkParam.getPageSize());
        page.setCurrent(feeParkParam.getCurrentPage());
        return this.baseMapper.getFeeParkList(page,feeParkParam.getUserName(),feeParkParam.getParkName());
    }
}
