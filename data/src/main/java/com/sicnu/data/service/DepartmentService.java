package com.sicnu.data.service;

import com.sicnu.data.Department;
import com.sicnu.data.Result;

public interface DepartmentService {
    Result addDepartment(String department_name);

    Result delDepartment(Integer department_id);

    Result findAllDepartment();
    Result updateDepartment(Department department);

}
