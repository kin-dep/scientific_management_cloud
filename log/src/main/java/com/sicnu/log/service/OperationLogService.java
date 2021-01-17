package com.sicnu.log.service;


import com.sicnu.log.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("DATA")
public interface OperationLogService {
    @PostMapping("/operationLog/findAllOperationLogs")
    Result findAllOperationLogs(@RequestParam Integer pageSize,@RequestParam Integer pageNum);

    @PostMapping("/operationLog/deleteAllOperationLogs")
    Result deleteAllOperationLogs();
}
