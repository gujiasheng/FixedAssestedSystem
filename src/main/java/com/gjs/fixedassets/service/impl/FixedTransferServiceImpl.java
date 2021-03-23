package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.FixedTransfer;
import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.mapper.FixedTransferMapper;
import com.gjs.fixedassets.service.FixedTransferService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FixedTransferServiceImpl implements FixedTransferService {
    @Resource
    private FixedTransferMapper fixedTransferMapper;


    @Override
    public List<Fixedcard> selectFixedTransferByCompanyIdPage(Integer companyId, Integer page, Integer limit, String fixedId, String fixedName) {
        int startNum = (page - 1) * limit;
        return fixedTransferMapper.selectFixedTransferByCompanyIdPage(companyId, startNum, limit, fixedId, fixedName);
    }

    @Override
    public List<Fixedcard> selectFixedTransferCount(Integer companyId, String fixedId, String fixedName) {
        return fixedTransferMapper.selectFixedTransferCount(companyId, fixedId, fixedName);
    }

    @Override
    public FixedTransfer selectFixedTransByCidFid(Integer companyId, Integer fixedcardId) {
        return fixedTransferMapper.selectFixedTransByCidFid(companyId, fixedcardId);
    }

    @Override
    public List<FixedTransfer> selectFixedTransByCompanyId(Integer companyId) {
        return fixedTransferMapper.selectFixedTransByCompanyId(companyId);
    }
}
