package com.sicnu.data.service;

import com.sicnu.data.AwardLevel;
import com.sicnu.data.Result;

public interface AwardLevelService {
    Result addAwardLevel(String al_name, Integer al_score);
    Result delAwardLevel(Integer al_id);
    Result findAllAwardLevel();
    Result updateAwardLevel(AwardLevel awardLevel);
}
