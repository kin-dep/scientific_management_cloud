package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.Department;
import com.sicnu.system_management.util.Result;

public interface DepartmentService {
    Result addDepartment(String department_name);

    Result delDepartment(Integer department_id);

    Result findAllDepartment();
    Result updateDepartment(Department department);

}
