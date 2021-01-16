package com.sicnu.achievement.service;

import com.sicnu.achievement.pojo.Paper;
import com.sicnu.achievement.util.Result;

import java.text.ParseException;

public interface PaperService {
    Result addPaper(Paper paper, String checkMessage, String message, Integer[] periodicalIds);

    Result selectPaperByCondition(Paper paper, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize) throws ParseException;

    Result updatePaper(Paper paper);

    Result delPaperById(Integer paper_id);

    Result selectAllPaperByCondition(Paper paper, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize) throws ParseException;

    Result findPaperById(Integer paper_id);

}
