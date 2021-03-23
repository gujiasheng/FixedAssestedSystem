package com.gjs.fixedassets.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.gjs.fixedassets.entity.Company;

import java.util.List;

@Mapper
public interface CompanyMapper {
    int insert(Company record);

    Company selectByPrimaryKey(Integer companyId);

    List<Company> selectAllCompany();

}