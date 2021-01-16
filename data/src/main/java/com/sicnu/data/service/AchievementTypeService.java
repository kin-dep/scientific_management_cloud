package com.sicnu.data.service;

import com.sicnu.data.AchievementType;
import com.sicnu.data.Result;

public interface AchievementTypeService {
    Result addAchievementType(String at_name);

    Result delAchievementType(Integer at_id);

    Result findAllAchievementType();
    Result updateAchievementType(AchievementType achievementType);

}
