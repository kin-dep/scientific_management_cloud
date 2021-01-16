package com.sicnu.login.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.awt.print.Paper;
import java.util.Map;

@Mapper
@Repository
public interface PaperMapper {
    Integer countPaper();
}
