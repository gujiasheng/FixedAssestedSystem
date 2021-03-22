package com.gjs.fixedassets.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fixedcard implements Serializable {
    /**
     * 固定资产卡片编号
     */
    private Integer fixedcardId;

    /**
     * 固定资产编号
     */
    private String fixedId;

    /**
     * 固定资产名称
     */
    private String fixedName;

    /**
     * 固定资产类别
     */
    private Integer fixedtypeId;

    private static final long serialVersionUID = 1L;

    /**
     * 预计寿命
     */
    private Integer workingLife;

    /**
     * 增加方式
     */
    private Integer incrementId;

    /**
     * 固定资产规格
     */
    private String fixedScale;

    /**
     * 使用状况
     */
    private Integer useStatus;

    /**
     * 已提计月份
     */
    private Integer accruedMonth;

    /**
     * 计量单位
     */
    private Integer measureUnit;

    /**
     * 数量
     */
    private Double account;

    /**
     * 入账价值
     */
    private Double totalAmount;

    /**
     * 单价
     */
    private Double unitPrice;

    /**
     * 折旧方法id
     */
    private Integer depreciationMethodId;

    /**
     * 当前存放位置
     */
    private String currentLocation;

    /**
     * 当前责任部门
     */
    private Integer departmentId;

    /**
     * 当前责任人id
     */
    private Integer personCharge;

    /**
     * 固定资产当前使用状态
     */
    private Integer fixedStatus;

    /**
     * 所属公司id
     */
    private Integer companyId;
    /**
     * 购置日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date acquisitionDate;
    /**
     * 录入日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date entryDate;
    /**
     * 开始使用日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startWorkingDate;

    /**
     * 预计残净值
     */
    private Double estimatedResidualValue;

    /**
     * 累计折旧
     */
    private Double accumulatedDepreciation;

    /**
     * 月折旧率
     */
    private Double monthlyDepreciationRate;

    /**
     * 月折旧额
     */
    private Double monthlyDepreciation;

    /**
     * 净值
     */
    private Double netWorth;
    /**
     * 预计残净值率
     */
    private Double estimatedResidualValueRate;

    /**
     * 来源渠道
     */
    private String supplier;

    /**
     * 公司
     */
    private Company company;
}