package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.CheckRecordStatus;
import com.gjs.fixedassets.mapper.CheckRecordStatusMapper;
import com.gjs.fixedassets.service.CheckRecordStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CheckRecordStatusServiceImpl implements CheckRecordStatusService {
    @Resource
    private CheckRecordStatusMapper checkRecordStatusMapper;


    @Override
    public CheckRecordStatus selectNewNodeByRecordId(Integer checkRecordId) {
        return checkRecordStatusMapper.selectNewNodeByRecordId(checkRecordId);
    }

    @Override
    public int insert(CheckRecordStatus checkRecordStatus) {

        return 0;
    }
}
