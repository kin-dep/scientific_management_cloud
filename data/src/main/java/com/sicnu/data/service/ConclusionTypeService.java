package com.sicnu.data.service;

import com.sicnu.data.ConclusionType;
import com.sicnu.data.Result;

public interface ConclusionTypeService {
    Result addConclusionType(String ct_name);

    Result delConclusionType(Integer ct_id);

    Result findAllConclusionType();
    Result updateConclusionType(ConclusionType conclusionType);

}
