package com.sicnu.login.controller;


import com.sicnu.login.service.impl.CountServiceImpl;
import com.sicnu.login.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
public class CountController {
    @Resource
    CountServiceImpl countService;
    private Result rs;

    @PostMapping("/count/all")
    public Result countAll() {
        try {
            rs = countService.countAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/count/check")
    public Result countChekc() {
        try {
            rs = countService.countCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
