package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.Level;
import com.sicnu.system_management.util.Result;

public interface LevelService {
    Result addLevel(String level_name, Integer pl_score);

    Result delLevel(Integer level_id);

    Result findAllLevel();
    Result updateLevel(Level level);

}
