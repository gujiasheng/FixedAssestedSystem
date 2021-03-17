package com.gjs.fixedassets.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable {
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

    private static final long serialVersionUID = 1L;
}