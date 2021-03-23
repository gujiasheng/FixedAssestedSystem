package com.gjs.fixedassets.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Companynature implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 公司性质id
     */
    private Integer companyNatureId;
    /**
     * 公司性质name
     */
    private String companyNatureName;
}