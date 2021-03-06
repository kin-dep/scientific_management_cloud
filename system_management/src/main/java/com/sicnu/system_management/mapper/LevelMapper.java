package com.sicnu.system_management.mapper;

import com.sicnu.system_management.pojo.Level;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LevelMapper {
    void addLevel(String level_name, Integer pl_score);

    void delLevel(Integer level_id);

    List<Level> findAllLevel();

    Level selectLevelByName(String level_name);
    void updateLevel(Level level);
    Integer selectLevelScoreById(Integer level_id);
}
