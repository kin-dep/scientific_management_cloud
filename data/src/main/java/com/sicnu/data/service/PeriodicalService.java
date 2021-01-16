package com.sicnu.data.service;

import com.sicnu.data.Periodical;
import com.sicnu.data.Result;

public interface PeriodicalService {
    Result addPeriodical(String periodical_name, Integer pp_score);
    Result delPeriodical(Integer periodical_d);
    Result findAllPeriodical();
    Result updatePeriodical(Periodical periodical);

}
