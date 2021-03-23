package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Companynature;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanynatureMapper {

    List<Companynature> selectCompanyNature();
}