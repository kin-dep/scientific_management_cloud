package com.sicnu.data.mapper;

import com.sicnu.data.PaperTeamExamine;
import com.sicnu.data.PaperTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperTeamExamineMapper {
    void addPaperTeamExamineUser(Integer pe_id, Integer user_id, Integer contribution);
    void delPaperTeamExamineTeam(Integer pe_id);
    List<PaperTeamExamine> selectPaperTeamExamineById(Integer pe_id);
    List<PaperTeamMap> selectPaperTeamExamineUser(Integer pe_id);
}
