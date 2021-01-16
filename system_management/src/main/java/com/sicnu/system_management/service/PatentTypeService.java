package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.PatentType;
import com.sicnu.system_management.util.Result;


public interface PatentTypeService {
    Result addPatentType(String pt_name);
    Result delPatentType(Integer pt_id);
    Result findAllPatentType();
    Result updatePatentType(PatentType patentType);
}
