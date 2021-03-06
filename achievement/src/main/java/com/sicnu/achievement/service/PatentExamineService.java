package com.sicnu.achievement.service;

import com.sicnu.achievement.pojo.PatentExamine;
import com.sicnu.achievement.util.Result;


public interface PatentExamineService {
    Result addPatentExamine(PatentExamine patentExamine, Integer[] user_id, Integer[] contribution);
    Result selectPatentExamineByCondition(PatentExamine patentExamine, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum,String apply_time_start,String apply_time_end);
    Result delPatentExamineById(Integer pe_id);
    Result findPatentExamineById(Integer pe_id);
}
