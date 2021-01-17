package com.sicnu.log.controller;




import com.sicnu.log.service.OperationLogService;
import com.sicnu.log.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operationLog")
public class OperationLogController {


    @Autowired
    OperationLogService operationLogService;

    private Result rs = null;

    @PostMapping("/findAllOperationLogs")
    public Result findAllOperationLogs(@RequestParam Integer pageSize,@RequestParam Integer pageNum) {
        try {
            rs = operationLogService.findAllOperationLogs(pageSize, pageNum);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
    @PostMapping("/deleteAllOperationLogs")
    public Result deleteAllOperationLogs() {
        try {
            rs=operationLogService.deleteAllOperationLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs ;

    }
}
