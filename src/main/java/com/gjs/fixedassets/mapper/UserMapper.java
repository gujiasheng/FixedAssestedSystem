package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 获取分页数据
    List<User> selectByCompanyId(Integer companyId, Integer page, Integer limit);

    //查询用户总数据
    List<User> selectAllUserCount(Integer companyId);

}