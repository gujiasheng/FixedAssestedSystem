package com.gjs.fixedassets.service;

import com.github.pagehelper.PageInfo;
import com.gjs.fixedassets.entity.User;

import java.util.List;

public interface UserService {
    //登录
    User selectUserByNamePSW(String userName, String password, Integer companyId);

    //  根据公司id查询所有人员
    List<User> selectByCompanyId(Integer companyId, int page, int limit, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus, String workId);

    //人员查寻结果的有多少人
    List<User> selectAllUserCount(Integer companyId, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus, String workId);

    //根据人员id查人
    User selectUserByUserId(Integer userId);

    //添加人员
    void addUser(User user);

    //修改人员
    void updateUser(User user);

    //删除
    void deleteUser(Integer userId);

    //下拉列表
    List<User> selectAllUserByCompanyId(Integer companyId);

    List<String> selectAllAccountName(Integer companyId);

    List<User> selectUserByDepartmentId(Integer departId);

}
