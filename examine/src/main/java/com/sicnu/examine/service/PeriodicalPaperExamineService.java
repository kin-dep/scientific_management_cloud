package com.sicnu.examine.service;


import com.sicnu.examine.util.Result;

public interface PeriodicalPaperExamineService {
    Result findPeriodicalExamineByPaperId(Integer pe_id);
}
