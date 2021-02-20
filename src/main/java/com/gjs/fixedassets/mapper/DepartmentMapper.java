package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Department;

public interface DepartmentMapper {
    Department selectByPrimaryKey(Integer departmentId);
}