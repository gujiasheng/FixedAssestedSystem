package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    Department selectByPrimaryKey(Integer departmentId);

    List<Department> selectDepartmentByCompanyId(@Param("companyId") Integer companyId);

    String selectDName(Integer departmentId);
}