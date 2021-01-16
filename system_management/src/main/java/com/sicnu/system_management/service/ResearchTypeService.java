package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.ResearchType;
import com.sicnu.system_management.util.Result;

public interface ResearchTypeService {
    Result addResearchType(String rt_name);
    Result delResearchType(Integer rt_id);
    Result findAllResearchType();
    Result updateResearchType(ResearchType researchType);
}
