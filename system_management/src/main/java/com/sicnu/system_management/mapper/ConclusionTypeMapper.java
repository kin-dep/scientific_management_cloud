package com.sicnu.system_management.mapper;

import com.sicnu.system_management.pojo.ConclusionType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ConclusionTypeMapper {
    void addConclusionType(String ct_name);

    void delConclusionType(Integer ct_id);

    List<ConclusionType> findAllConclusionType();

    ConclusionType selectConclusionTypeByName(String ct_name);
    void updateConclusionType(ConclusionType conclusionType);
}
