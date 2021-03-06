package com.sicnu.system_management.mapper;

import com.sicnu.system_management.pojo.PatentType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatentTypeMapper {
    void addPatentType(String pt_name);
    void delPatentType(Integer pt_id);
    List<PatentType> findAllPatentType();
    PatentType selectPatentTypeByName(String pt_name);
    void updatePatentType(PatentType patentType);
}
