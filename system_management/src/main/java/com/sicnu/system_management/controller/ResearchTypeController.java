package com.sicnu.system_management.controller;

import com.sicnu.system_management.pojo.ResearchType;
import com.sicnu.system_management.service.impl.ResearchTypeServiceImpl;
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
public class ResearchTypeController {
    @Resource
    ResearchTypeServiceImpl researchTypeService;
    private Result rs;

    @PostMapping("/researchType/addResearchType")
    @RequiresPermissions("/data")
   public Result addResearchType(String rt_name) {
        try {
            rs = researchTypeService.addResearchType(rt_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/researchType/delResearchType")
    @RequiresPermissions("/data")
    public Result delResearchType(Integer rt_id) {

        try {
            rs = researchTypeService.delResearchType(rt_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/researchType/findAllResearchType")
    public Result findAllResearchType() {
        try {
            rs = researchTypeService.findAllResearchType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/researchType/updateResearchType")
    @RequiresPermissions("/data")
    public Result updateResearchType(ResearchType researchType) {

        try {
            rs =researchTypeService.updateResearchType(researchType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
