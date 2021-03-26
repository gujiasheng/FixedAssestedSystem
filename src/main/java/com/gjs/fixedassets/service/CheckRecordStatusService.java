package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.CheckRecordStatus;

public interface CheckRecordStatusService {

    CheckRecordStatus selectNewNodeByRecordId(Integer checkRecordId);
}
