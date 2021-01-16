package com.sicnu.data.service;

import com.sicnu.data.AwardRank;
import com.sicnu.data.Result;

public interface AwardRankService {
    Result addAwardRank(String ar_name, Integer ar_score);
    Result delAwardRank(Integer ar_id);
    Result findAllAwardRank();
    Result updateAwardRank(AwardRank awardRank);
}
