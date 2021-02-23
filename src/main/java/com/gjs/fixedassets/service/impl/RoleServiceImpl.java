package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.Role;
import com.gjs.fixedassets.mapper.RoleMapper;
import com.gjs.fixedassets.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }

    @Override
    public String selectRName(Integer roleId) {
        return roleMapper.selectRName(roleId);
    }


}
