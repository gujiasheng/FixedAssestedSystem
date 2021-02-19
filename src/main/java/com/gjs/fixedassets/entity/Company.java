package com.gjs.fixedassets.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * @Description TODO
 * 公司实体
 * @Author 顾嘉晟
 * @Date 2021-02-18
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司所属行业id
     */
    private Integer companyIndustryId;

    /**
     * 公司性质
     */
    private Integer companyNatureId;

    /**
     * 公司邮箱
     */
    private String companyEmail;
}