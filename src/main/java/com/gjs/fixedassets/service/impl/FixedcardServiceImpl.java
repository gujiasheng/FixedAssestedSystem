package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.mapper.FixedcardMapper;
import com.gjs.fixedassets.service.FixedcardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FixedcardServiceImpl implements FixedcardService {
    @Resource
    private FixedcardMapper fixedcardMapper;

    @Override
    public void insertFixedAsssetCard(Fixedcard fixedcard) {
        fixedcardMapper.insertFixedAsssetCard(fixedcard);
    }

    @Override
    public List<Fixedcard> selectFixedAsssetByCompany(Integer companyId) {
        return fixedcardMapper.selectFixedAsssetByCompany(companyId);
    }

    @Override
    public List<String> selectFixedIdList(Integer companyId) {
        return fixedcardMapper.selectFixedIdList(companyId);
    }

    @Override
    public List<Fixedcard> selectFixedByCompanyIdPage(Integer companyId, Integer page, Integer limit, String fixedId, String fixedName, Integer useStatus) {
        int startNum = (page - 1) * limit;

        return fixedcardMapper.selectFixedByCompanyIdPage(companyId, startNum, limit, fixedId, fixedName, useStatus);
    }

    @Override
    public List<Fixedcard> selectFixedCount(Integer companyId, String fixedId, String fixedName, Integer useStatus) {
        return fixedcardMapper.selectFixedCount(companyId, fixedId, fixedName, useStatus);
    }

    @Override
    public Fixedcard selectFixedByFixedCardId(Integer fixedCardId) {
        return fixedcardMapper.selectFixedByFixedCardId(fixedCardId);
    }

    @Override
    public void updateFixedByFixedCardId(Fixedcard fixedCard) {
        fixedcardMapper.updateFixedByFixedCardId(fixedCard);
    }

    @Override
    public List<Fixedcard> selectFixedcardByUserId(Integer userId) {
        return fixedcardMapper.selectFixedcardByUserId(userId);
    }
}
