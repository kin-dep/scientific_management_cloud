package com.sicnu.login.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface BookMapper {


    Integer countBook();
}
