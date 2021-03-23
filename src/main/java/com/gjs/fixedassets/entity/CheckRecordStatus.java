package com.gjs.fixedassets.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class CheckRecordStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 审核记录id
     */
    private Integer checkRecordStatusId;
    /**
     * 审核类型id
     */
    private Integer checkTypeId;
    /**
     * 审核节点id
     */
    private Integer checkNodeId;
    /**
     * 具体审核单id
     */
    private Integer checkRecordId;
    /**
     * 备注
     */
    private String remark;
}