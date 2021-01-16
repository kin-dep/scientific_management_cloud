package com.sicnu.check.controller;


import com.sicnu.check.service.impl.CheckServiceImpl;
import com.sicnu.check.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class CheckController {
    @Resource
    CheckServiceImpl checkService;

    private Result rs;

    @PostMapping("/check/selectCheckByCondition")
    public Result selectAllCheckByCondition(Integer user_id,Integer department_id,String check_time) {
        try {
            rs = checkService.selectAllCheckByCondition(user_id, department_id, check_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/check/selectPersonalCheckByCondition")
    public Result selectPersonalCheckByCondition(Integer department_id,String check_time) {
        try {
            rs = checkService.selectPersonalCheckByCondition(department_id, check_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @GetMapping("/check/test")
    public Result checkTest() {
        checkService.addAllFinalCheck();
        return new Result("200", "测试完成", null);
    }
}
