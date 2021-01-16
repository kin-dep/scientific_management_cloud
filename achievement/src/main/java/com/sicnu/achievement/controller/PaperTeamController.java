package com.sicnu.achievement.controller;


import com.sicnu.achievement.pojo.PaperTeam;
import com.sicnu.achievement.service.impl.PaperTeamServiceImpl;
import com.sicnu.achievement.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class PaperTeamController {
    @Resource
    PaperTeamServiceImpl paperTeamService;

    private Result rs;
    @PostMapping("/team/addPaperTeamUser")
    public Result addPaperTeamUser(Integer paper_id, Integer user_id, Integer contribution) {
        try {
            System.err.println("paper_id: " + paper_id + " user_id: " + user_id + " contribution: " + contribution);
            rs=paperTeamService.addPaperTeamUser(paper_id, user_id, contribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/team/delPaperTeamUser")
    public Result delPaperTeamUser(Integer paper_id,Integer user_id) {
        try {
            rs=paperTeamService.delPaperTeamUser(paper_id,user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/team/selectPaperTeam")
    public Result selectPaperTeam(Integer paper_id) {
        try {
            rs = paperTeamService.selectPaperTeam(paper_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/team/updatePaperTeamUser")
    public Result updatePaperTeamUser(PaperTeam paperTeam) {
        try {
            rs=paperTeamService.updatePaperTeamUser(paperTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
