package com.gjs.fixedassets.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Companyindustry implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 公司所属行业id
     */
    private Integer companyIndustryId;
    /**
     * 公司所属行业name
     */
    private String companyIndustryName;
}