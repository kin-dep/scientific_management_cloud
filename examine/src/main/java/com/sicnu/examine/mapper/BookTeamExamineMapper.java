package com.sicnu.examine.mapper;


import com.sicnu.examine.pojo.BookTeamExamine;
import com.sicnu.examine.pojo.BookTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookTeamExamineMapper {
    void addBookTeamExamineUser(Integer be_id, Integer user_id,Integer contribution);
    void delBookTeamExamineTeam(Integer be_id);
    List<BookTeamExamine> selectBookTeamExamineById(Integer be_id);
    List<BookTeamMap> selectBookTeamExamineUser(Integer be_id);
}
