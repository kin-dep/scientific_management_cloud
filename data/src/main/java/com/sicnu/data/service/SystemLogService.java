package com.sicnu.data.service;

import com.sicnu.data.Result;

public interface SystemLogService {
    Result findAllSystemLogs(Integer pageSize, Integer pageNum);
    Result deleteAllSystemLogs();
}
