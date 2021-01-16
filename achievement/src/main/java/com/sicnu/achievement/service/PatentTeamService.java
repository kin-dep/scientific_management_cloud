package com.sicnu.achievement.service;

import com.sicnu.achievement.pojo.PatentTeam;
import com.sicnu.achievement.util.Result;


public interface PatentTeamService {

    Result addPatentTeamUser(Integer patent_id,Integer user_id,  Integer contribution);
    Result delPatentTeamUser(Integer patent_id,Integer user_id);
    Result selectPatentTeam(Integer patent_id);
    Result updatePatentTeamUser(PatentTeam patentTeam);

}
