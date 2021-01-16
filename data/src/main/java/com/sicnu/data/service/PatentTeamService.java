package com.sicnu.data.service;

import com.sicnu.data.PatentTeam;
import com.sicnu.data.Result;


public interface PatentTeamService {

    Result addPatentTeamUser(Integer patent_id, Integer user_id, Integer contribution);
    Result delPatentTeamUser(Integer patent_id, Integer user_id);
    Result selectPatentTeam(Integer patent_id);
    Result updatePatentTeamUser(PatentTeam patentTeam);

}
