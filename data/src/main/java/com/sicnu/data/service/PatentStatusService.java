package com.sicnu.data.service;

import com.sicnu.data.PatentStatus;
import com.sicnu.data.Result;

public interface PatentStatusService {
    Result addPatentStatus(String ps_name);
    Result delPatentStatus(Integer ps_id);
    Result findAllPatentStatus();
    Result updatePatentRange(PatentStatus patentStatus);
}
