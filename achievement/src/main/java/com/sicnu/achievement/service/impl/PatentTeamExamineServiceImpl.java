package com.sicnu.achievement.service.impl;


import com.alibaba.fastjson.JSON;
import com.sicnu.achievement.mapper.PatentTeamExamineMapper;
import com.sicnu.achievement.mapper.RoleMapper;
import com.sicnu.achievement.pojo.PatentTeamMap;
import com.sicnu.achievement.service.PatentTeamExamineService;
import com.sicnu.achievement.util.Result;
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
