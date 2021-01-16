package com.sicnu.log.mapper;


import com.sicnu.log.pojo.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OperationLogMapper {
    List<OperationLog> findAllOperationLogs(Map<String, Object> map);
    Integer countAllOperationLogs();
    void deleteAllOperationLogs();
}
