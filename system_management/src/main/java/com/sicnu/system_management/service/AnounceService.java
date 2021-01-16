package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.Anounce;
import com.sicnu.system_management.util.Result;

import java.util.Map;

public interface AnounceService {
    Result addAnounce(Anounce anounce);
    Result delAnounceById(Integer anounce_id);
    Result updateAnounce(Anounce anounce);
    Result selectAllAnounce(Map<String, Object> condition);
    Result selectAnounceById(Integer anounce_id);
}
