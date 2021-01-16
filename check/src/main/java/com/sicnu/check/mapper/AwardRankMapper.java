package com.sicnu.check.mapper;


import com.sicnu.check.pojo.AwardRank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AwardRankMapper {
    void addAwardRank(String ar_name,Integer ar_score);
    void delAwardRank(Integer ar_id);
    List<AwardRank> findAllAwardRank();
    AwardRank selectAwardRankByName(String ar_name);
    void updateAwardRank(AwardRank awardRank);
    Integer selectAwardRankScoreById(Integer ar_id);
}
