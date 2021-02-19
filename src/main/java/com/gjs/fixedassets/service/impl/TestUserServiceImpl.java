package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.TestUser;
import com.gjs.fixedassets.mapper.TestuserMapper;
import com.gjs.fixedassets.service.TestUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestUserServiceImpl implements TestUserService {

    @Resource
    private TestuserMapper testuserMapper;

    @Override
    public List<TestUser> findAll() {
        return testuserMapper.findAll();
    }
}
