package com.sicnu.service;

import com.sicnu.pojo.PaperTeam;
import com.sicnu.util.Result;

public interface PaperTeamService {
    Result addPaperTeamUser(Integer paper_id, Integer user_id, Integer contribution);
    Result delPaperTeamUser(Integer paper_id, Integer user_id);
    Result selectPaperTeam(Integer paper_id);
    Result updatePaperTeamUser(PaperTeam paperTeam);
}
