package com.sicnu.log.controller;


import com.sicnu.log.pojo.LogsListResult;
import com.sicnu.log.service.impl.SystemLogServiceImpl;
import com.sicnu.log.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class SystemLogController {
    @Resource
    SystemLogServiceImpl systemLogService;

    private Result rs = null;

    @PostMapping("/logs/findAllSystemLogs")

    public Result findAllSystemLogs(Integer pageSize, Integer pageNum) {
        LogsListResult logsListResult = null;
        try {
            rs = systemLogService.findAllSystemLogs(pageSize, pageNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs ;
    }

    @PostMapping("/logs/deleteAllSystemLogs")

    public Result deleteAllSystemLogs() {
        try {
            rs = systemLogService.deleteAllSystemLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs ;
    }
}
