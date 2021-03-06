package com.sicnu.check.mapper;


import com.sicnu.check.pojo.AwardLevel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public
interface AwardLevelMapper {
    void addAwardLevel(String al_name,Integer al_score);
    void delAwardLevel(Integer al_id);
    List<AwardLevel> findAllAwardLevel();
    AwardLevel selectAwardLevelByName(String al_name);
    void updateAwardLevel(AwardLevel awardLevel);
    Integer selectAwardLevelScoreById(Integer al_id);
}
