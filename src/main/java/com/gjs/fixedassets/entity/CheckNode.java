package com.gjs.fixedassets.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class CheckNode implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 审核节点id
     */
    private Integer checkNodeId;
    /**
     * 审核节点名称
     */
    private String chenkNodeName;
    /**
     * 审核节点顺序
     */
    private Integer checkNodeOrder;
    /**
     * 审核类型
     */
    private Integer checkTypeId;
}