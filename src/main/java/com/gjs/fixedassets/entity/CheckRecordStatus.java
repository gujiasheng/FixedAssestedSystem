package com.gjs.fixedassets.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CheckRecordStatus implements Serializable {
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

    private static final long serialVersionUID = 1L;
    /**
     * 审核人id
     */
    private Integer checkMan;
    /**
     * 审核时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date checkTime;

    /**
     * 我的消息类
     */
    private Mymessage mymessage;

    /**
     * 领用类
     */
    private FixedTransfer fixedTransfer;

    /**
     * 审核节点
     */
    private CheckNode checkNode;


}