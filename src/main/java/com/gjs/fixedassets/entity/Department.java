package com.gjs.fixedassets.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 部门id
     */
    private Integer departmentId;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 公司id
     */
    private Integer companyId;
    /**
     * 部门经理id
     */
    private Integer departmentManager;
}