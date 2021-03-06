package com.sicnu.system_management.mapper;

import com.sicnu.system_management.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentMapper {
    void addDepartment(String department_name);

    void delDepartment(Integer department_id);

    List<Department> findAllDepartment();

    Department selectDepartmentByName(String department_name);
    void updateDepartment(Department department);

    String selectDepartmentById(Integer department_id);
}
