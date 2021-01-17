package com.sicnu.examine.service.impl;


import com.sicnu.examine.mapper.PeriodicalPaperExamineMapper;
import com.sicnu.examine.service.PeriodicalPaperExamineService;
import org.springframework.stereotype.Service;
import com.sicnu.examine.util.Result;
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
