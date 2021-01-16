package com.sicnu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.data.PatentTeamExamineMapper;
import com.sicnu.data.RoleMapper;
import com.sicnu.data.PatentTeamMap;
import com.sicnu.data.PatentTeamExamineService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PatentTeamExamineServiceImpl implements PatentTeamExamineService {

    @Resource
    PatentTeamExamineMapper patentTeamExamineMapper;
    @Resource
    RoleMapper roleMapper;
    private Result rs;
    @Override
    public Result selectPatentTeamExamineUser(Integer pe_id) {
        try {
            List<PatentTeamMap> patentTeamMaps = patentTeamExamineMapper.selectPatentTeamExamineUser(pe_id);
            List<Map> mapList = new ArrayList<>();
            for(PatentTeamMap ptm : patentTeamMaps) {
                Map map = JSON.parseObject(JSON.toJSONString(ptm), Map.class);
                map.put("role_name", roleMapper.selectRoleName(ptm.getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
