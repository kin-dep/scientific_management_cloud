package com.sicnu.examine.controller;


import com.sicnu.examine.pojo.AwardExamine;
import com.sicnu.examine.service.impl.AwardExamineServiceImpl;
import com.sicnu.examine.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class AwardExamineController {

    @Resource
    AwardExamineServiceImpl awardExamineService;

    private Result rs;

    @GetMapping("/awardExamine/addAwardExamine")
    public Result addAwardExamine(AwardExamine awardExamine, Integer[] user_id, Integer[] contribution) {
        try {
            rs=awardExamineService.addAwardExamine(awardExamine,user_id,contribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/awardExamine/selectAwardExamineByCondition")
    public Result selectAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        try {
            rs = awardExamineService.selectAwardExamineByCondition(awardExamine, award_time_start, award_time_end, pageSize, pageNum, apply_time_start, apply_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/awardExamine/delAwardExamineById")
    public Result delAwardExamineById(Integer ae_id) {
        rs=awardExamineService.delAwardExamineById(ae_id);
        return rs;
    }

    @PostMapping("/awardExamine/selectAllAwardExamineByCondition")
    public Result selectAllAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        try {
            rs = awardExamineService.selectAllAwardExamineByCondition(awardExamine, award_time_start, award_time_end, pageSize, pageNum, apply_time_start, apply_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/awardExamine/findAwardExamineById")
    public Result findAwardExamineById(Integer ae_id) {
        try {
            rs = awardExamineService.findAwardExamineById(ae_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/awardExamine/findPersonalAwardExamineMessage")
    public Result findPersonalAwardExamineMessage(Integer ae_id){
        try {
            rs = awardExamineService.findPersonalAwardExamineMessage(ae_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
