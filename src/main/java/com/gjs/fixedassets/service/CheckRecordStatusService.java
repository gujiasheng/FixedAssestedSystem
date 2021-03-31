package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.CheckRecordStatus;

import java.util.List;

public interface CheckRecordStatusService {

    CheckRecordStatus selectNewNodeByRecordId(Integer checkRecordId);

    int insert(CheckRecordStatus checkRecordStatus);

    CheckRecordStatus selectNewRecordTrans(Integer recordId);

}
