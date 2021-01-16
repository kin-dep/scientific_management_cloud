package com.sicnu.system_management.controller;

import com.sicnu.system_management.pojo.PressLevel;
import com.sicnu.system_management.service.impl.PressLevelServiceImpl;
import com.sicnu.system_management.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class PressLevelController {

    @Resource
    PressLevelServiceImpl pressLevelService;

    private Result rs;

    @PostMapping("/pressLevel/addPressLevel")
    @RequiresPermissions("/data")
    public Result addPressLevel(String pl_name,Integer bpl_score) {
        try {
            rs = pressLevelService.addPressLevel(pl_name,bpl_score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/pressLevel/delPressLevel")
    @RequiresPermissions("/data")
    public Result delPressLevel(Integer pl_id) {
        try {
           rs = pressLevelService.delPressLevel(pl_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/pressLevel/findAllPressLevel")
    public Result findAllPressLevel() {
        try {
            rs = pressLevelService.findAllPressLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/pressLevel/updatePressLevel")
    @RequiresPermissions("/data")
    public Result updatePressLevel(PressLevel pressLevel) {
        try {
            rs=pressLevelService.updatePressLevel(pressLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
