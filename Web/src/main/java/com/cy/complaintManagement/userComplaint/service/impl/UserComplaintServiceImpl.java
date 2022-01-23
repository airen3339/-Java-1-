package com.cy.complaintManagement.userComplaint.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.complaintManagement.userComplaint.entity.UserComplaint;
import com.cy.complaintManagement.userComplaint.mapper.UserComplaintMapper;
import com.cy.complaintManagement.userComplaint.service.UserComplaintService;
import org.springframework.stereotype.Service;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-22 22:59:46
 */
@Service
public class UserComplaintServiceImpl extends ServiceImpl<UserComplaintMapper, UserComplaint> implements UserComplaintService {
}
