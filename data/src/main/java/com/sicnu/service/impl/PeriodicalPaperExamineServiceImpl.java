package com.sicnu.service.impl;

import com.sicnu.mapper.PeriodicalPaperExamineMapper;
import com.sicnu.service.PeriodicalPaperExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Service
@RestController
@RequestMapping("/periodicalPaperExamine")
public class PeriodicalPaperExamineServiceImpl implements PeriodicalPaperExamineService {

    @Resource
    PeriodicalPaperExamineMapper periodicalPaperExamineMapper;

    private Result rs;

    @Override
    @RequestMapping("/findPeriodicalExamineByPaperId")

    public Result findPeriodicalExamineByPaperId(Integer pe_id) {
        try {
            List<Integer> periodicals = periodicalPaperExamineMapper.findPeriodicalExamineByPaperId(pe_id);
            rs = new Result("200", "成果获取期刊id列表", periodicals);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
