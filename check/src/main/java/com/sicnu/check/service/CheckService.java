package com.sicnu.check.service;


import com.sicnu.check.util.Result;

public interface CheckService {
    void addAllFinalCheck();
    Result selectAllCheckByCondition(Integer user_id, Integer department_id, String check_time);

}
