package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentService {

    List<Department> selectDepartmentByCompanyId(@Param("companyId") Integer companyId);
}
