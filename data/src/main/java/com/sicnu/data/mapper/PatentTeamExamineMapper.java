package com.sicnu.data.mapper;

import com.sicnu.data.PatentTeamExamine;
import com.sicnu.data.PatentTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatentTeamExamineMapper {

    void addPatentTeamExamineUser(Integer pe_id, Integer user_id, Integer contribution);
    void delPatentTeamExamineTeam(Integer pe_id);
    List<PatentTeamExamine> selectPatentTeamExamineById(Integer pe_id);
    List<PatentTeamMap> selectPatentTeamExamineUser(Integer pe_id);
}
