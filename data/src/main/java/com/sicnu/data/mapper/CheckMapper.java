package com.sicnu.data.mapper;

import com.sicnu.data.Check;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CheckMapper {
    void addCheck(Check check);
    List<Check> selectCheckByCondition(Map<String, Object> map);
    void delCheck();
}
