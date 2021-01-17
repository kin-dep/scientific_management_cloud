package com.sicnu.service;

import com.sicnu.util.Result;

public interface SystemLogService {
    Result findAllSystemLogs(Integer pageSize, Integer pageNum);
    Result deleteAllSystemLogs();
}
