package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.mapper.FixedcardMapper;
import com.gjs.fixedassets.service.FixedcardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FixedcardServiceImpl implements FixedcardService {
    @Resource
    private FixedcardMapper fixedcardMapper;

    @Override
    public void insertFixedAsssetCard(Fixedcard fixedcard) {
        fixedcardMapper.insertFixedAsssetCard(fixedcard);
    }
}
