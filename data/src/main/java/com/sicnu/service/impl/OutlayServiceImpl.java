package com.sicnu.service.impl;

import com.sicnu.mapper.OutlayMapper;
import com.sicnu.pojo.Outlay;
import com.sicnu.service.OutlayService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Service
@RestController
@RequestMapping("/outlay")
public class OutlayServiceImpl implements OutlayService {
    @Resource
    private OutlayMapper outlayMapper;
    private Result rs;
    @RequestMapping("/addOutlay")
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
    @RequestMapping("/selectAllOutlay")
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
    @RequestMapping("/delOutlayById")
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
    @RequestMapping("/updateOutlay")
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
    @RequestMapping("/selectOutlayById")
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
