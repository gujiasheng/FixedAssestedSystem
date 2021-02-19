package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Role;

public interface RoleMapper {
    Role selectByPrimaryKey(Integer roleId);
}