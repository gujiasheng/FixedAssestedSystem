package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.CheckRecordStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckRecordStatusService {

    CheckRecordStatus selectNewNodeByRecordId(Integer checkRecordId);

    int insert(CheckRecordStatus checkRecordStatus);

    CheckRecordStatus selectNewRecordTrans(Integer recordId);

    List<String> selectRemarkByRidNid(@Param("recordId") Integer recordId);


}
