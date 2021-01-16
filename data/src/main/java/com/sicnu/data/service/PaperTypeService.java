package com.sicnu.data.service;

import com.sicnu.data.PaperType;
import com.sicnu.data.Result;

public interface PaperTypeService {
    Result addPaperType(String pt_name);
    Result delPaperType(Integer pt_id);
    Result findAllPaperType();
    Result updatePaperType(PaperType paperType);
}
