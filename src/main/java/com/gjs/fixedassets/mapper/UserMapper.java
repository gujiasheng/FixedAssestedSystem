package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.User;

public interface UserMapper {
    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}