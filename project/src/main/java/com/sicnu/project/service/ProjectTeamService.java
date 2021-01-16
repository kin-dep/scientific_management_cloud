package com.sicnu.project.service;


import com.sicnu.project.pojo.ProjectTeam;
import com.sicnu.project.util.Result;


public interface ProjectTeamService {
    Result addProjectTeamUser(Integer project_id, Integer user_id,String team_role);

    Result delProjectTeamUser(Integer project_id,Integer user_id);

    Result selectProjectTeam(Integer project_id);
    Result updateProjectTeamUser(ProjectTeam projectTeam);

}
