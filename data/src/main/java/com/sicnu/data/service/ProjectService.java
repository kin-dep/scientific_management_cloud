package com.sicnu.data.service;

import com.sicnu.data.Project;
import com.sicnu.data.Result;

import javax.mail.MessagingException;

public interface ProjectService {
    Result addProject(Project project, String checkMessage, String message) throws MessagingException;

    Result selectProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String plan_time_start, String plan_time_end, String complete_time_end, Integer pageNum, Integer pageSize) throws Exception;

    Result delProject(Integer project_id);

    Result updateProject(Project project);

    Result selectTeamByPid(Integer project_Id);

    Result selectAllProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize) throws Exception;

    Result findProjectById(Integer project_id);
}
