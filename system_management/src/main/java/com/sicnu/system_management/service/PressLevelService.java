package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.PressLevel;
import com.sicnu.system_management.util.Result;

public interface PressLevelService {
    Result addPressLevel(String pl_name, Integer bpl_score);
    Result delPressLevel(Integer pl_id);
    Result findAllPressLevel();
    Result updatePressLevel(PressLevel pressLevel);

}
