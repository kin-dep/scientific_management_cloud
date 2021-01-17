package com.sicnu.service;

import com.sicnu.pojo.Department;
import com.sicnu.util.Result;

public interface DepartmentService {
    Result addDepartment(String department_name);

    Result delDepartment(Integer department_id);

    Result findAllDepartment();
    Result updateDepartment(Department department);

}
