package com.sicnu.data.mapper;

import com.sicnu.data.AwardTeamExamine;
import com.sicnu.data.AwardTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AwardTeamExamineMapper {
    void addAwardExamineTeamUser(Integer ae_id, Integer user_id, Integer contribution);
    void delAwardTeamExamineTeam(Integer ae_id);
    List<AwardTeamExamine> selectAwardTeamExamineById(Integer ae_id);
    List<AwardTeamMap> selectAwardTeamExamineUser(Integer ae_id);
}
