package com.sicnu.data.service;

import com.sicnu.data.PaperTeam;
import com.sicnu.data.Result;

public interface PaperTeamService {
    Result addPaperTeamUser(Integer paper_id, Integer user_id, Integer contribution);
    Result delPaperTeamUser(Integer paper_id, Integer user_id);
    Result selectPaperTeam(Integer paper_id);
    Result updatePaperTeamUser(PaperTeam paperTeam);
}
