package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.PatentRange;
import com.sicnu.system_management.util.Result;

public interface PatentRangeService {
    Result addPatentRange(String pr_name);
    Result delPatentRange(Integer pr_id);
    Result findAllPatentRange();
    Result updatePatentRange(PatentRange patentRange);
}
