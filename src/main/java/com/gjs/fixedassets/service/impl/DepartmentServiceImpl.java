package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.mapper.DepartmentMapper;
import com.gjs.fixedassets.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectDepartmentByCompanyId(Integer companyId) {
        return departmentMapper.selectDepartmentByCompanyId(companyId);
    }

    @Override
    public String selectDName(Integer departmentId) {
        return departmentMapper.selectDName(departmentId);
    }
}
