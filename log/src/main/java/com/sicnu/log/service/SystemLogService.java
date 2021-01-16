package com.sicnu.log.service;

import com.sicnu.log.util.Result;

public interface SystemLogService {
    Result findAllSystemLogs(Integer pageSize, Integer pageNum);
    Result deleteAllSystemLogs();
}
