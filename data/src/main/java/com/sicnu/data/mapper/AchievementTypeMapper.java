package com.sicnu.data.mapper;

import com.sicnu.data.AchievementType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AchievementTypeMapper {
    void addAchievementType(String at_name);

    void delAchievementType(Integer at_id);

    List<AchievementType> findAllAchievementType();

    AchievementType selectAchievementTypeByName(String at_name);
    void updateAchievementType(AchievementType achievementType);
}
