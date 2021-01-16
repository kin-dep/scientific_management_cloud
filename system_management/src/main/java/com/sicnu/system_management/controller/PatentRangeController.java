package com.sicnu.system_management.controller;

import com.sicnu.system_management.pojo.PatentRange;
import com.sicnu.system_management.service.impl.PatentRangeServiceImpl;
import com.sicnu.system_management.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class PatentRangeController {

    @Resource
    PatentRangeServiceImpl patentRangeService;
    private Result rs;

    @PostMapping("/patentRange/addPatentRange")
    @RequiresPermissions("/data")
    public Result addPatentRange(String pr_name) {
        try {
            rs =patentRangeService.addPatentRange(pr_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentRange/delPatentRange")
    @RequiresPermissions("/data")
    public Result delPatentRange(Integer pr_id) {
        try {
            rs = patentRangeService.delPatentRange(pr_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentRange/findAllPatentRange")
    public Result findAllPatentRange() {
        try {
            rs = patentRangeService.findAllPatentRange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentRange/updatePatentRange")
    @RequiresPermissions("/data")
    public Result updatePatentRange(PatentRange patentRange) {
        try {
            rs =patentRangeService.updatePatentRange(patentRange);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
