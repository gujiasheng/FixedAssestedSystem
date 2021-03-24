package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.CheckRecordStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckRecordStatusMapper {

    int insert(CheckRecordStatus checkRecordStatus);
}