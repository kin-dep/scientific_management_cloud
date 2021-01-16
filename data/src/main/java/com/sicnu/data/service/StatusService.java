package com.sicnu.data.service;

import com.sicnu.data.Status;
import com.sicnu.data.Result;

public interface StatusService {
    Result addStatus(String status_name);

    Result delStatus(Integer status_id);

    Result findAllStatus();
    Result updateStatus(Status status);

}
