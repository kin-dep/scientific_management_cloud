package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.AchievementType;
import com.sicnu.system_management.util.Result;

public interface AchievementTypeService {
    Result addAchievementType(String at_name);

    Result delAchievementType(Integer at_id);

    Result findAllAchievementType();
    Result updateAchievementType(AchievementType achievementType);

}
