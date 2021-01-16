package com.sicnu.log.controller;


import com.sicnu.log.pojo.LogsListResult;
import com.sicnu.log.service.impl.OperationLogServiceImpl;
import com.sicnu.log.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class OperationLogController {


    @Resource
    OperationLogServiceImpl operationLogService;

    private Result rs = null;

    @PostMapping("/logs/findAllOperationLogs")
    public Result findAllOperationLogs(Integer pageSize, Integer pageNum) {
        LogsListResult logsListResult = null;
        try {
            rs = operationLogService.findAllOperationLogs(pageSize, pageNum);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
    @PostMapping("/logs/deleteAllOperationLogs")
    public Result deleteAllOperationLogs() {
        try {
            rs=operationLogService.deleteAllOperationLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs ;

    }
}
