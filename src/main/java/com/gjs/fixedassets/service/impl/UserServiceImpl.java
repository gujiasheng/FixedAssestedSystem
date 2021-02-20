package com.gjs.fixedassets.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.mapper.UserMapper;
import com.gjs.fixedassets.service.UserService;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectByCompanyId(Integer companyId, int page, int limit) {
        int startNum = (page - 1) * limit;
        List<User> lists = userMapper.selectByCompanyId(companyId, startNum, limit);
        return lists;
    }

    @Override
    public List<User> selectAllUserCount(Integer companyId) {
        return userMapper.selectAllUserCount(companyId);
    }


}
