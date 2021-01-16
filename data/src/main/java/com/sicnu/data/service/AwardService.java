package com.sicnu.data.service;

import com.sicnu.data.Award;
import com.sicnu.data.Result;

import java.text.ParseException;


public interface AwardService {

    Result addAward(Award award, String checkMessage, String message);

    Result selectAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException;

    Result updateAward(Award award);

    Result delAwardById(Integer award_id);
    Result selectAllAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException ;
    Result findAwardById(Integer award_id);
}
