package com.sicnu.data.mapper;

import com.sicnu.data.PeriodicalPaper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PeriodicalPaperMapper {
    void addPeriodicalPaper(Integer paper_id, Integer periodical_id);
    List<Integer> findPeriodicalByPaperId(Integer paper_id);
    void delPeriodicalByPaperId(Integer paper_id);
    void delPeriodicalByPeriodicalId(Integer periodical_id);
    void updatePeriodicalPaper(PeriodicalPaper periodicalPaper);
}
