package com.sicnu.data.service;

import com.sicnu.data.PressLevel;
import com.sicnu.data.Result;

public interface PressLevelService {
    Result addPressLevel(String pl_name, Integer bpl_score);
    Result delPressLevel(Integer pl_id);
    Result findAllPressLevel();
    Result updatePressLevel(PressLevel pressLevel);

}
