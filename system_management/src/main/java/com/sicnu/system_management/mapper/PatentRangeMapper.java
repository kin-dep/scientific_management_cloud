package com.sicnu.system_management.mapper;

import com.sicnu.system_management.pojo.PatentRange;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatentRangeMapper {
    void addPatentRange(String pr_name);
    void delPatentRange(Integer pr_id);
    List<PatentRange> findAllPatentRange();
    PatentRange selectPatentRangeByName(String pr_name);
    void updatePatentRange(PatentRange patentRange);
}
