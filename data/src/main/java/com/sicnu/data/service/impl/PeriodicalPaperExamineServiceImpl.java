package com.sicnu.data.service.impl;

import com.sicnu.data.PeriodicalPaperExamineMapper;
import com.sicnu.data.PeriodicalPaperExamineService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PeriodicalPaperExamineServiceImpl implements PeriodicalPaperExamineService {

    @Resource
    PeriodicalPaperExamineMapper periodicalPaperExamineMapper;

    private Result rs;

    @Override
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
