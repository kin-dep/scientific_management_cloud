package com.sicnu.data.service;

import com.sicnu.data.Nature;
import com.sicnu.data.Result;

public interface NatureService {
    Result addNature(String nature_name);

    Result delNature(Integer nature_id);

    Result findAllNature();
    Result updateNature(Nature nature);

}
