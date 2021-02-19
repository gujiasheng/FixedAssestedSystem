package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.User;

import java.util.List;

public interface UserService {

    List<User> selectByCompanyId(Integer companyId);

}
