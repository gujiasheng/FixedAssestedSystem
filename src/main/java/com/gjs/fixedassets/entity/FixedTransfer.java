package com.gjs.fixedassets.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class FixedTransfer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 领用单主键
     */
    private Integer fixedTransferId;
    /**
     * 领用单号显示的编号
     */
    private String fixedTransferId2;
    /**
     * 领用资产id
     */
    private Integer fixedcardId;
    /**
     * 审核类型
     */
    private Integer checkTypeId;
    /**
     * 是否通过
     */
    private Integer isPass;
    /**
     * 领用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date fixedTransferTime;
    /**
     * 领用备注
     */
    private String transferRemark;
}