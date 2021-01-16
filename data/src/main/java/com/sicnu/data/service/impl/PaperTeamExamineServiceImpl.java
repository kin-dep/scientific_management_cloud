package com.sicnu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.data.PaperTeamExamineMapper;
import com.sicnu.data.RoleMapper;
import com.sicnu.data.PaperTeamMap;
import com.sicnu.data.PaperTeamExamineService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PaperTeamExamineServiceImpl implements PaperTeamExamineService {
    @Resource
    PaperTeamExamineMapper paperTeamExamineMapper;

    @Resource
    RoleMapper roleMapper;

    private Result rs;
    @Override
    public Result selectPaperTeamExamineUser(Integer pe_id) {
        try {
            List<PaperTeamMap> paperTeamMaps = paperTeamExamineMapper.selectPaperTeamExamineUser(pe_id);
            List<Map> mapList = new ArrayList<>();
            for(PaperTeamMap paperTeamMap : paperTeamMaps) {
                Map map = JSON.parseObject(JSON.toJSONString(paperTeamMap), Map.class);
                map.put("role_name", roleMapper.selectRoleName(paperTeamMap.getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
