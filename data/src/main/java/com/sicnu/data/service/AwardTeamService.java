package com.sicnu.data.service;

import com.sicnu.data.AwardTeam;
import com.sicnu.data.Result;

public interface AwardTeamService {
    Result addAwardTeamUser(Integer award_id, Integer user_id, Integer contribution);
    Result delAwardTeamUser(Integer award_id, Integer user_id);
    Result selectAwardTeam(Integer award_id);
    Result updateAwardTeamUser(AwardTeam awardTeam);

}
