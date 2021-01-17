package com.sicnu.service;

import com.sicnu.pojo.Status;
import com.sicnu.util.Result;

public interface StatusService {
    Result addStatus(String status_name);

    Result delStatus(Integer status_id);

    Result findAllStatus();
    Result updateStatus(Status status);

}
