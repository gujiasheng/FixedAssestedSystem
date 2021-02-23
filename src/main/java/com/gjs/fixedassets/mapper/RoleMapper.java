package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectAllRole();

    String selectRName(Integer roleId);

}