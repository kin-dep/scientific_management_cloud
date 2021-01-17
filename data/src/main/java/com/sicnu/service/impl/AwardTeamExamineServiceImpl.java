package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.AwardTeamExamineMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.teamMap.AwardTeamMap;
import com.sicnu.service.AwardTeamExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RestController
@RequestMapping("/awardTeamExamine")
public class AwardTeamExamineServiceImpl implements AwardTeamExamineService {
    @Resource
    AwardTeamExamineMapper awardTeamExamineMapper;
    @Resource
    RoleMapper roleMapper;
    private Result rs;
    @Override
    @RequestMapping("/selectAwardTeamExamineUser")
    public Result selectAwardTeamExamineUser(Integer ae_id) {
        try {
            List<AwardTeamMap> awardTeamMaps = awardTeamExamineMapper.selectAwardTeamExamineUser(ae_id);
            List<Map> mapList = new ArrayList<>();
            for(AwardTeamMap atm : awardTeamMaps) {
                Map map = JSON.parseObject(JSON.toJSONString(atm), Map.class);
                map.put("role_name", roleMapper.selectRoleName(atm.getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
