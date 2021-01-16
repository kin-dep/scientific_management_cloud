package com.sicnu.data.service;

import com.sicnu.data.Outlay;
import com.sicnu.data.Result;

public interface OutlayService {
    Result addOutlay(Outlay outlay);
    Result selectAllOutlay();
    Result delOutlayById(Integer outlay_id);
    Result updateOutlay(Outlay outlay);
    Result selectOutlayById(Integer outlay_id);
}
