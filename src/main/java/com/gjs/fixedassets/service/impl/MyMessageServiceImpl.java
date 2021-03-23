package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.Mymessage;
import com.gjs.fixedassets.mapper.MymessageMapper;
import com.gjs.fixedassets.service.MyMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyMessageServiceImpl implements MyMessageService {
    @Resource
    private MymessageMapper mymessageMapper;


    @Override
    public List<Mymessage> selectMessageLimit(Integer userId) {
        return mymessageMapper.selectMessageLimit(userId);
    }

    @Override
    public List<Mymessage> selectMessagePage(Integer userId, Integer page, Integer limit) {
        Integer startNum = (page - 1) * limit;
        return mymessageMapper.selectMessagePage(userId, startNum, limit);
    }

    @Override
    public List<Mymessage> selectMessageCount(Integer userId) {
        return mymessageMapper.selectMessageCount(userId);
    }
}
