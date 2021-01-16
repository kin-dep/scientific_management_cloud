package com.sicnu.log.service;


import com.sicnu.log.util.Result;

public interface OperationLogService {
    Result findAllOperationLogs(Integer pageSize, Integer pageNum);
    Result deleteAllOperationLogs();
}
