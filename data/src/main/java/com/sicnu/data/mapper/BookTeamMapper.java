package com.sicnu.data.mapper;

import com.sicnu.data.BookTeam;
import com.sicnu.data.BookTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookTeamMapper {

    void addBookTeamUser(Integer book_id, Integer user_id, Integer contribution);
    void delBookTeamUser(Integer book_id, Integer user_id);
    List<BookTeamMap> selectBookTeam(Integer book_id);
    void updateBookTeamUser(BookTeam bookTeam);
}
