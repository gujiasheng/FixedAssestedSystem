package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Companyindustry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyindustryMapper {

    List<Companyindustry> selectIndustry();
}