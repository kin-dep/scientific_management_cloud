package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.ConclusionType;
import com.sicnu.system_management.util.Result;

public interface ConclusionTypeService {
    Result addConclusionType(String ct_name);

    Result delConclusionType(Integer ct_id);

    Result findAllConclusionType();
    Result updateConclusionType(ConclusionType conclusionType);

}
