package com.sicnu.data.service;


import com.sicnu.data.ProjectTeam;
import com.sicnu.data.Result;


public interface ProjectTeamService {
    Result addProjectTeamUser(Integer project_id, Integer user_id, String team_role);

    Result delProjectTeamUser(Integer project_id, Integer user_id);

    Result selectProjectTeam(Integer project_id);
    Result updateProjectTeamUser(ProjectTeam projectTeam);

}
