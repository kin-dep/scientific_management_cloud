package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.AwardLevel;
import com.sicnu.system_management.util.Result;

public interface AwardLevelService {
    Result addAwardLevel(String al_name, Integer al_score);
    Result delAwardLevel(Integer al_id);
    Result findAllAwardLevel();
    Result updateAwardLevel(AwardLevel awardLevel);
}
