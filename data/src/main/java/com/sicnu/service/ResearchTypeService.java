package com.sicnu.service;

import com.sicnu.pojo.ResearchType;
import com.sicnu.util.Result;

public interface ResearchTypeService {
    Result addResearchType(String rt_name);
    Result delResearchType(Integer rt_id);
    Result findAllResearchType();
    Result updateResearchType(ResearchType researchType);
}
