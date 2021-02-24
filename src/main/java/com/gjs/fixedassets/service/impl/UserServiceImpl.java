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
    public User selectUserByNamePSW(String userName, String password) {
        return userMapper.selectUserByNamePSW(userName, password);
    }

    @Override
    public List<User> selectByCompanyId(Integer companyId, int page, int limit, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus, String workId) {
        int startNum = (page - 1) * limit;


        List<User> lists = userMapper.selectByCompanyId(companyId, startNum, limit, userName, phone, departmentId, roleId, isStatus, workId);
        return lists;
    }

    @Override
    public List<User> selectAllUserCount(Integer companyId, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus, String workId) {
        return userMapper.selectAllUserCount(companyId, userName, phone, departmentId, roleId, isStatus, workId);
    }

    @Override
    public User selectUserByUserId(Integer userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }


}
