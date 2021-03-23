package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.Company;
import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.Job;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.mapper.CompanyMapper;
import com.gjs.fixedassets.mapper.DepartmentMapper;
import com.gjs.fixedassets.mapper.JobMapper;
import com.gjs.fixedassets.mapper.UserMapper;
import com.gjs.fixedassets.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private JobMapper jobMapper;

    @Override
    public void registerCompany(Company company, Department department, User user, Job job) {
        companyMapper.insert(company);
        job.setCompanyId(company.getCompanyId());
//            job.setDepartmentId(department.getDepartmentId());
        job.setJobName("员工");
        jobMapper.insert(job);
        user.setIsStatus(1);
        user.setCompanyId(company.getCompanyId());
        user.setJobId(job.getJobId());
        user.setRoleId(1);
        user.setWorkId("10001");
        user.setGender("1");
        userMapper.addUser2(user);
        department.setCompanyId(company.getCompanyId());
        department.setDepartmentName("系统管理部");
        department.setDepartmentShowId(10001);
        department.setDepartmentManagerObj(user);
        department.setDepartmentManager(user.getUserId());
        departmentMapper.addDepartment2(department);

        user.setDepartmentId(department.getDepartmentId());

        userMapper.updateUser(user);


    }

    @Override
    public List<Company> selectAllCompany() {
        return companyMapper.selectAllCompany();
    }
}
