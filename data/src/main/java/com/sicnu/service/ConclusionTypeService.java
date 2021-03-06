package com.sicnu.service;

import com.sicnu.pojo.ConclusionType;
import com.sicnu.util.Result;

public interface ConclusionTypeService {
    Result addConclusionType(String ct_name);

    Result delConclusionType(Integer ct_id);

    Result findAllConclusionType();
    Result updateConclusionType(ConclusionType conclusionType);

}
