package com.cy.complaintManagement.userComplaint.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description: 业主投诉表
 * @date 2022-01-22 22:57:34
 */
@Data
@TableName("user_complaint")
public class UserComplaint implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long complaintId;

    /**
     * 投诉人id
     */
    @NotNull
    private Long userId;

    /**
     * 标题
     */
    @NotBlank
    private String title;

    /**
     * 投诉内容
     */
    @NotBlank
    private String complaintContent;

    /**
     * 投诉时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;

    /**
     * 处理状态 0：未处理 1：已处理
     */
    private String status;
}
