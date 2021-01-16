package com.sicnu.examine.service;


import com.sicnu.examine.pojo.PaperExamine;
import com.sicnu.examine.util.Result;

public interface PaperExamineService {
    Result addPaperExamine(PaperExamine paperExamine, Integer[] user_id, Integer[] contribution, Integer[] periodicalIds);
    Result selectPaperExamineByCondition(PaperExamine paperExamine, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end);
    Result delPaperExamineById(Integer pe_id);
    Result findPaperExamineById(Integer pe_id);

}
