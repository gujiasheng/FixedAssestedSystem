package com.gjs.fixedassets.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ְ职位id
     */
    private Integer jobId;
    /**
     * ְ职位name
     */
    private String jobName;
    /**
     * 职位所属部门
     */
    private Integer departmentId;
    /**
     * 职位所属公司
     */
    private Integer companyId;

    /**
     * 所属部门名称
     */
    private String departmentName;

    /**
     * 职位等级
     */
    private Integer level;
}