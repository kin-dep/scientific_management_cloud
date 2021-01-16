package com.sicnu.data.service;

import com.sicnu.data.Result;

public interface CheckService {
    void addAllFinalCheck();
    Result selectAllCheckByCondition(Integer user_id, Integer department_id, String check_time);

}
