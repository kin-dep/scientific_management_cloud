package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.DepartmentRank;
import com.sicnu.system_management.util.Result;

public interface DepartmentRankService {
    Result addDepartmentRank(String dr_name);
    Result delDepartmentRank(Integer dr_id);
    Result findAllDepartmentRank();
    Result updateDepartmentRank(DepartmentRank departmentRank);
}
