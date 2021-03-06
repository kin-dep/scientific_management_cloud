package com.sicnu.system_management.mapper;

import com.sicnu.system_management.pojo.ResearchType;
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
