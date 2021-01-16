package com.sicnu.data.service;

import com.sicnu.data.ResearchType;
import com.sicnu.data.Result;

public interface ResearchTypeService {
    Result addResearchType(String rt_name);
    Result delResearchType(Integer rt_id);
    Result findAllResearchType();
    Result updateResearchType(ResearchType researchType);
}
