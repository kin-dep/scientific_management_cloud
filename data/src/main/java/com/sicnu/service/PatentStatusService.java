package com.sicnu.service;

import com.sicnu.pojo.PatentStatus;
import com.sicnu.util.Result;

public interface PatentStatusService {
    Result addPatentStatus(String ps_name);
    Result delPatentStatus(Integer ps_id);
    Result findAllPatentStatus();
    Result updatePatentRange(PatentStatus patentStatus);
}
