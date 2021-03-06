package com.sicnu.achievement.controller;


import com.sicnu.achievement.service.impl.PeriodicalPaperServiceImpl;
import com.sicnu.achievement.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RestController
public class PeriodicalPaperController {
    @Resource
    PeriodicalPaperServiceImpl periodicalPaperService;
    private Result rs;

    @PostMapping("/periodicalPaper/addPeriodicalPaper")
    public Result addPeriodicalPaper(Integer paper_id, Integer periodical_id) {
        try {
            rs=periodicalPaperService.addPeriodicalPaper(paper_id, periodical_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/periodicalPaper/findPeriodicalByPaperId")
    public Result findPeriodicalByPaperId(Integer paper_id) {
        try {
            rs = periodicalPaperService.findPeriodicalByPaperId(paper_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/periodicalPaper/delPeriodicalByPaperId")
   public Result delPeriodicalByPaperId(Integer paper_id) {
        try {
            rs=periodicalPaperService.delPeriodicalByPaperId(paper_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/periodicalPaper/delPeriodicalByPeriodicalId")
    public Result delPeriodicalByPeriodicalId(Integer periodical_id) {
        try {
            rs=periodicalPaperService.delPeriodicalByPeriodicalId(periodical_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @GetMapping("/periodicalPaper/updatePeriodicalPaper")
    public Result updatePeriodicalPaper(Integer paper_id,Integer[] periodical_ids) {
        try {
            rs =periodicalPaperService.updatePeriodicalPaper(paper_id,periodical_ids);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
