package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.PatentStatus;
import com.sicnu.system_management.util.Result;

public interface PatentStatusService {
    Result addPatentStatus(String ps_name);
    Result delPatentStatus(Integer ps_id);
    Result findAllPatentStatus();
    Result updatePatentRange(PatentStatus patentStatus);
}
