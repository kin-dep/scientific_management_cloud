package com.sicnu.service;

import com.sicnu.pojo.Outlay;
import com.sicnu.util.Result;

public interface OutlayService {
    Result addOutlay(Outlay outlay);
    Result selectAllOutlay();
    Result delOutlayById(Integer outlay_id);
    Result updateOutlay(Outlay outlay);
    Result selectOutlayById(Integer outlay_id);
}
