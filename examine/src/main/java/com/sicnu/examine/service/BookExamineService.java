package com.sicnu.examine.service;


import com.sicnu.examine.pojo.BookExamine;
import com.sicnu.examine.util.Result;

public interface BookExamineService {
    Result addBookExamine(BookExamine bookExamine, Integer[] user_id, Integer[] contribution);
    Result selectBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum,String apply_time_start,String apply_time_end);
    Result delBookExamineById(Integer be_id);
    Result findBookExamineById(Integer be_id);

}
