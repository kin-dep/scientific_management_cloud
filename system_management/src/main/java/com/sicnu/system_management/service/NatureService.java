package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.Nature;
import com.sicnu.system_management.util.Result;

public interface NatureService {
    Result addNature(String nature_name);

    Result delNature(Integer nature_id);

    Result findAllNature();
    Result updateNature(Nature nature);

}
