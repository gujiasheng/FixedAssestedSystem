package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.TestUser;
import com.gjs.fixedassets.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestuserMapper {

    List<TestUser> findAll();

}