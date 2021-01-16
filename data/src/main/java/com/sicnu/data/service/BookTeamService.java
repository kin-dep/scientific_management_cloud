package com.sicnu.data.service;


import com.sicnu.data.BookTeam;
import com.sicnu.data.Result;


public interface BookTeamService {

    Result addBookTeamUser(Integer book_id, Integer user_id, Integer contribution);
    Result delBookTeamUser(Integer book_id, Integer user_id);
    Result selectBookTeam(Integer book_id);
    Result updateBookTeamUser(BookTeam bookTeam);

}
