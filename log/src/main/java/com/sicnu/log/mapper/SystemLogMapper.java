package com.sicnu.log.mapper;


import com.sicnu.log.pojo.SystemLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SystemLogMapper {
    List<SystemLog> findAllSystemLogs(Map<String,Object> map);
    Integer countAllSystemLogs();
    void deleteAllSystemLogs();
}
