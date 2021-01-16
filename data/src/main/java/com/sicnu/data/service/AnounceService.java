package com.sicnu.data.service;

import com.sicnu.data.Anounce;
import com.sicnu.data.Result;

import java.util.Map;

public interface AnounceService {
    Result addAnounce(Anounce anounce);
    Result delAnounceById(Integer anounce_id);
    Result updateAnounce(Anounce anounce);
    Result selectAllAnounce(Map<String, Object> condition);
    Result selectAnounceById(Integer anounce_id);
}
