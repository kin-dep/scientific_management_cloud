package com.sicnu.service;

import com.sicnu.pojo.AwardRank;
import com.sicnu.util.Result;

public interface AwardRankService {
    Result addAwardRank(String ar_name, Integer ar_score);
    Result delAwardRank(Integer ar_id);
    Result findAllAwardRank();
    Result updateAwardRank(AwardRank awardRank);
}
