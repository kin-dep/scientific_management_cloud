package com.sicnu.service;

import com.sicnu.pojo.AchievementType;
import com.sicnu.util.Result;

public interface AchievementTypeService {
    Result addAchievementType(String at_name);

    Result delAchievementType(Integer at_id);

    Result findAllAchievementType();
    Result updateAchievementType(AchievementType achievementType);

}
