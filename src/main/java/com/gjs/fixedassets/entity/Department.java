package com.gjs.fixedassets.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
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

    private static final long serialVersionUID = 1L;
    /**
     * 部门第二负责人
     */
    private Integer departmentManager2;
    /**
     * 部门电话
     */
    private Integer departmentPhone;
    /**
     * 部门显示id
     */
    private Integer departmentShowId;
    /**
     * 用户对象
     */
    private User departmentManagerObj;
}