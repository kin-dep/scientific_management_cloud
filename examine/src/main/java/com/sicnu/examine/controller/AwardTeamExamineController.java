package com.sicnu.examine.controller;


import com.sicnu.examine.service.impl.AwardTeamExamineServiceImpl;
import com.sicnu.examine.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class AwardTeamExamineController {

    @Resource
    AwardTeamExamineServiceImpl awardTeamExamineService;
    private Result rs;
    @PostMapping("/teamExamine/selectAwardTeamExamineUser")
    public Result selectAwardTeamExamineUser(Integer ae_id) {
        try {
           rs = awardTeamExamineService.selectAwardTeamExamineUser(ae_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
