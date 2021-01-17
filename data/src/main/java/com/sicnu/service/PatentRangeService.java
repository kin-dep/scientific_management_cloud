package com.sicnu.service;

import com.sicnu.pojo.PatentRange;
import com.sicnu.util.Result;

public interface PatentRangeService {
    Result addPatentRange(String pr_name);
    Result delPatentRange(Integer pr_id);
    Result findAllPatentRange();
    Result updatePatentRange(PatentRange patentRange);
}
