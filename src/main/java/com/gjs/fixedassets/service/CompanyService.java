package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.Company;
import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.Job;
import com.gjs.fixedassets.entity.User;

import java.util.List;

public interface CompanyService {

    void registerCompany(Company company, Department department, User user, Job job);

    List<Company> selectAllCompany();

}
