package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.PaperType;
import com.sicnu.system_management.util.Result;

public interface PaperTypeService {
    Result addPaperType(String pt_name);
    Result delPaperType(Integer pt_id);
    Result findAllPaperType();
    Result updatePaperType(PaperType paperType);
}
