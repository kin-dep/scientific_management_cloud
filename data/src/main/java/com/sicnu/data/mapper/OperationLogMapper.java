package com.sicnu.data.mapper;

import com.sicnu.data.OperationLog;
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
