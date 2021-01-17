package com.sicnu.log.controller;



import com.sicnu.log.service.SystemLogService;
import com.sicnu.log.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/systemLogs")
public class SystemLogController {
    @Autowired
    SystemLogService systemLogService;

    private Result rs = null;

    @PostMapping("/findAllSystemLogs")
    public Result findAllSystemLogs(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        try {
            rs = systemLogService.findAllSystemLogs(pageSize, pageNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs ;
    }

    @PostMapping("/deleteAllSystemLogs")
    public Result deleteAllSystemLogs() {
        try {
            rs = systemLogService.deleteAllSystemLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs ;
    }
}
