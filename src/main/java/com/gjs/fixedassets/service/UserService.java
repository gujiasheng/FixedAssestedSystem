package com.gjs.fixedassets.service;

import com.github.pagehelper.PageInfo;
import com.gjs.fixedassets.entity.User;

import java.util.List;

public interface UserService {
    //  根据公司id查询所有人员
    List<User> selectByCompanyId(Integer companyId, int page, int limit, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus);

    List<User> selectAllUserCount(Integer companyId, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus);

}
