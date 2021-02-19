package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Company;

public interface CompanyMapper {
    int insert(Company record);

    Company selectByPrimaryKey(Integer companyId);
}