package com.sicnu.data.service;

import com.sicnu.data.PatentType;
import com.sicnu.data.Result;


public interface PatentTypeService {
    Result addPatentType(String pt_name);
    Result delPatentType(Integer pt_id);
    Result findAllPatentType();
    Result updatePatentType(PatentType patentType);
}
