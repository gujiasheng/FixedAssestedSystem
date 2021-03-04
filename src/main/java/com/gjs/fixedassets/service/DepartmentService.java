package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentService {

    List<Department> selectDepartmentByCompanyId(@Param("companyId") Integer companyId, Integer page, Integer limit, Integer departmentId);

    String selectDName(Integer departmentId);

    List<Department> selectAllDepartmentCount(@Param("companyId") Integer companyId, @Param("departmentId") Integer departmentId);

    List<Department> selectDepartmentByCompanyId2(@Param("companyId") Integer companyId);

    void addDepartment(Department department);
}
