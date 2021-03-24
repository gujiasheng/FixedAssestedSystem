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
    private Integer fixedTransferId2;
    /**
     * 领用资产id
     */
    private Integer fixedcardId;
    /**
     * 领用资产信息
     */
    private Fixedcard fixedcard;

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

    private String fixedTransferTime;

    /**
     * 使用地点
     */
    private String usePosition;
    /**
     * 领用备注
     */
    private String transferRemark;
    /**
     * 所属公司id
     */
    private Integer companyId;
    /**
     * 领用人信息
     */
    private User receiver;

    /**
     * 部门负责人姓名
     */
    private String departmentManager;
    /**
     * 资产负责人
     */
    private String personCharge;

    /**
     * 部门名
     */
    private String departmentName;
}