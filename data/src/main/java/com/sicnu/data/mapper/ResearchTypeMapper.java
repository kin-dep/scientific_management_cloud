package com.sicnu.data.mapper;

import com.sicnu.data.ResearchType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResearchTypeMapper {
    void addResearchType(String rt_name);
    void delResearchType(Integer rt_id);
    List<ResearchType> findAllResearchType();
    ResearchType selectResearchTypeByName(String rt_name);
     void updateResearchType(ResearchType researchType);
}
