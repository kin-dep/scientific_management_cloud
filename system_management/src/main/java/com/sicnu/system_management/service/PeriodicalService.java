package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.Periodical;
import com.sicnu.system_management.util.Result;

public interface PeriodicalService {
    Result addPeriodical(String periodical_name, Integer pp_score);
    Result delPeriodical(Integer periodical_d);
    Result findAllPeriodical();
    Result updatePeriodical(Periodical periodical);

}
