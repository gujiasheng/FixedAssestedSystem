package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.mapper.UserMapper;
import com.gjs.fixedassets.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectByCompanyId(Integer companyId) {
        return userMapper.selectByCompanyId(companyId);
    }
}
