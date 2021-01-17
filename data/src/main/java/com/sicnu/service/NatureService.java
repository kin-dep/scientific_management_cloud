package com.sicnu.service;

import com.sicnu.pojo.Nature;
import com.sicnu.util.Result;

public interface NatureService {
    Result addNature(String nature_name);

    Result delNature(Integer nature_id);

    Result findAllNature();
    Result updateNature(Nature nature);

}
