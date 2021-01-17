package com.sicnu.examine.controller;


import com.sicnu.examine.service.PeriodicalPaperExamineService;
import com.sicnu.examine.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RestController
public class PeriodicalPaperExamineController {
    @Resource
    PeriodicalPaperExamineService periodicalPaperExamineService;
    private Result rs;

    @PostMapping("/periodicalExamine/findPeriodicalExamineByPaperId")
    public Result findPeriodicalByPaperId(Integer pe_id) {
        try {
            rs = periodicalPaperExamineService.findPeriodicalExamineByPaperId(pe_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
