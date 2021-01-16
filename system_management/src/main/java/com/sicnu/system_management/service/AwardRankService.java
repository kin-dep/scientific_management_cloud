package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.AwardRank;
import com.sicnu.system_management.util.Result;

public interface AwardRankService {
    Result addAwardRank(String ar_name, Integer ar_score);
    Result delAwardRank(Integer ar_id);
    Result findAllAwardRank();
    Result updateAwardRank(AwardRank awardRank);
}
