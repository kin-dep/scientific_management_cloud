package com.sicnu.data.service;

import com.sicnu.data.PatentRange;
import com.sicnu.data.Result;

public interface PatentRangeService {
    Result addPatentRange(String pr_name);
    Result delPatentRange(Integer pr_id);
    Result findAllPatentRange();
    Result updatePatentRange(PatentRange patentRange);
}
