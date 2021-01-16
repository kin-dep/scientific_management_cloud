package com.sicnu.data.mapper;

import com.sicnu.data.ProjectTeamExamine;
import com.sicnu.data.ProjectTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectTeamExamineMapper {
    void addProjectTeamExamineUser(Integer pe_id, Integer user_id, String team_role);
    void delProjectTeamExamineTeam(Integer pe_id);
    List<ProjectTeamExamine> selectProjectTeamExamineById(Integer pe_id);
    List<ProjectTeamMap> selectProjectTeamExamineUser(Integer pe_id);
}
