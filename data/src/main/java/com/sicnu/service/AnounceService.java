package com.sicnu.service;

import com.sicnu.pojo.Anounce;
import com.sicnu.util.Result;

import java.util.Map;

public interface AnounceService {
    Result addAnounce(Anounce anounce);
    Result delAnounceById(Integer anounce_id);
    Result updateAnounce(Anounce anounce);
    Result selectAllAnounce(Map<String, Object> condition);
    Result selectAnounceById(Integer anounce_id);
}
