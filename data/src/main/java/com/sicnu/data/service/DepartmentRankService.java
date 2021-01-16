package com.sicnu.data.service;

import com.sicnu.data.DepartmentRank;
import com.sicnu.data.Result;

public interface DepartmentRankService {
    Result addDepartmentRank(String dr_name);
    Result delDepartmentRank(Integer dr_id);
    Result findAllDepartmentRank();
    Result updateDepartmentRank(DepartmentRank departmentRank);
}
