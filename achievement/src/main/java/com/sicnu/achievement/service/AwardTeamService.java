package com.sicnu.achievement.service;

import com.sicnu.achievement.pojo.AwardTeam;
import com.sicnu.achievement.util.Result;

public interface AwardTeamService {
    Result addAwardTeamUser(Integer award_id, Integer user_id, Integer contribution);
    Result delAwardTeamUser(Integer award_id,Integer user_id);
    Result selectAwardTeam(Integer award_id);
    Result updateAwardTeamUser(AwardTeam awardTeam);

}
