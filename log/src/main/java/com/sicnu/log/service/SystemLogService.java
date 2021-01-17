package com.sicnu.log.service;

import com.sicnu.log.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("data")
public interface SystemLogService {
    @PostMapping("/systemLogs/findAllSystemLogs")
    Result findAllSystemLogs(@RequestParam Integer pageSize,@RequestParam Integer pageNum);
    @PostMapping("/systemLogs/deleteAllSystemLogs")
    Result deleteAllSystemLogs();
}
