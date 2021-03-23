package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DepartmentMapper {
    Department selectByPrimaryKey(Integer departmentId);

    List<Department> selectDepartmentByCompanyId(@Param("companyId") Integer companyId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("departmentId") Integer departmentId);

    String selectDName(Integer departmentId);

    List<Department> selectAllDepartmentCount(@Param("companyId") Integer companyId, @Param("departmentId") Integer departmentId);

    //部门下拉列表
    List<Department> selectDepartmentByCompanyId2(@Param("companyId") Integer companyId);

    //添加部门
    void addDepartment(Department department);

    int addDepartment2(Department department);
    //删除部门
    void deleteDepartment(Integer department);

    //修改部门信息
    void updateDepartment(Department department);
}