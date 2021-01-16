package com.sicnu.examine.controller;


import com.sicnu.examine.service.impl.BookTeamExamineServiceImpl;
import com.sicnu.examine.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RestController
public class BookTeamExamineController {
    @Resource
    BookTeamExamineServiceImpl bookTeamExamineService;
    private Result rs;

    @PostMapping("/teamExamine/selectBookTeamExamineUser")
    public Result selectBookTeamExamineUser(Integer be_id) {
        try {
            rs= bookTeamExamineService.selectBookTeamExamineUser(be_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
