package com.sicnu.login.service;



import com.sicnu.login.pojo.Anounce;
import com.sicnu.login.util.Result;

import java.util.Map;

public interface AnounceService {
    Result addAnounce(Anounce anounce);
    Result delAnounceById(Integer anounce_id);
    Result updateAnounce(Anounce anounce);
    Result selectAllAnounce(Map<String, Object> condition);
    Result selectAnounceById(Integer anounce_id);
}
