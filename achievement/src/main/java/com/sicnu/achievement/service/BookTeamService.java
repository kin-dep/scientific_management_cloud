package com.sicnu.achievement.service;


import com.sicnu.achievement.pojo.BookTeam;
import com.sicnu.achievement.util.Result;


public interface BookTeamService {

    Result addBookTeamUser(Integer book_id,Integer user_id, Integer contribution);
    Result delBookTeamUser(Integer book_id,Integer user_id);
    Result selectBookTeam(Integer book_id);
    Result updateBookTeamUser(BookTeam bookTeam);

}
