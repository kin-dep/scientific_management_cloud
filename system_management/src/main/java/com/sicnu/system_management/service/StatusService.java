package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.Status;
import com.sicnu.system_management.util.Result;

public interface StatusService {
    Result addStatus(String status_name);

    Result delStatus(Integer status_id);

    Result findAllStatus();
    Result updateStatus(Status status);

}
