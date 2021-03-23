package com.gjs.fixedassets.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Checktype implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 审批流程主键id
     */
    private Integer checkTypeId;
    /**
     * 审批流程名称
     */
    private String checkTypeName;
}