package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 根据分页，条件查询数据
    List<User> selectByCompanyId(Integer companyId, Integer page, Integer limit, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus);

    //查询用户总数据
    List<User> selectAllUserCount(Integer companyId, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus);

}