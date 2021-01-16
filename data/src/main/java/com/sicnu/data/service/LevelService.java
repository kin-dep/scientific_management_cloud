package com.sicnu.data.service;

import com.sicnu.data.Level;
import com.sicnu.data.Result;

public interface LevelService {
    Result addLevel(String level_name, Integer pl_score);

    Result delLevel(Integer level_id);

    Result findAllLevel();
    Result updateLevel(Level level);

}
