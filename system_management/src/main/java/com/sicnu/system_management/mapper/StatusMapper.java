package com.sicnu.system_management.mapper;

import com.sicnu.system_management.pojo.Status;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StatusMapper {
    void addStatus(String status_name);

    void delStatus(Integer status_id);

    List<Status> findAllStatus();

    Status selectStatusByName(String status_name);
    void updateStatus(Status status);
}
