package com.sicnu.achievement.mapper;


import com.sicnu.achievement.pojo.PaperTeam;
import com.sicnu.achievement.pojo.PaperTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperTeamMapper {

    void addPaperTeamUser(Integer paper_id,Integer user_id,Integer contribution);
    void delPaperTeamUser(Integer paper_id,Integer user_id);
    List<PaperTeamMap> selectPaperTeam(Integer paper_id);
    void updatePaperTeamUser(PaperTeam paperTeam);
}
