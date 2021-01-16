package com.sicnu.data.service;

import com.sicnu.data.Result;


public interface OperationLogService {
    Result findAllOperationLogs(Integer pageSize, Integer pageNum);
    Result deleteAllOperationLogs();
}
