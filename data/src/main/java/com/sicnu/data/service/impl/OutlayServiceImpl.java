package com.sicnu.data.service.impl;

import com.sicnu.data.OutlayMapper;
import com.sicnu.data.Outlay;
import com.sicnu.data.OutlayService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OutlayServiceImpl implements OutlayService {
    @Resource
    private OutlayMapper outlayMapper;
    private Result rs;
    @Override
    public Result addOutlay(Outlay outlay) {
        try {
            Integer res = outlayMapper.checkOutlay(outlay);
            if(res == 0){
                outlayMapper.addOutlay(outlay);
                rs = new Result("200","插入成功",null);
            }
            else rs = new Result("400", "非法区间", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectAllOutlay() {
        try {
            List<Outlay> outlays = outlayMapper.selectAllOutlay();
            rs = new Result("200",null,outlays);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delOutlayById(Integer outlay_id) {
        try {
            outlayMapper.delOutlayById(outlay_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateOutlay(Outlay outlay) {
        try {
            outlayMapper.updateOutlay(outlay);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectOutlayById(Integer outlay_id) {
        try {
            Outlay outlay = outlayMapper.selectOutlayById(outlay_id);
            rs = new Result("200", "获取经费区间信息成功", outlay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}
