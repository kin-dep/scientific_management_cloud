package com.sicnu.data.service;

import com.sicnu.data.Result;

public interface LogsService {
    Result findAllLogs(Integer pageSize, Integer pageNum);
    Result deleteAllLogs();
}
