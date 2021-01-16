package com.sicnu.examine.controller;


import com.sicnu.examine.service.impl.PaperTeamExamineServiceImpl;
import com.sicnu.examine.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
public class PaperTeamExamineController {
    @Resource
    PaperTeamExamineServiceImpl paperTeamExamineService;

    private Result rs;
    @PostMapping("/teamExamine/selectPaperTeamExamineUser")
    public Result selectPaperTeamExamineUser(Integer pe_id) {
        try {
            rs = paperTeamExamineService.selectPaperTeamExamineUser(pe_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
