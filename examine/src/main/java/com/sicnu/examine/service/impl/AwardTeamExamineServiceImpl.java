package com.sicnu.examine.service.impl;


import com.alibaba.fastjson.JSON;
import com.sicnu.examine.mapper.AwardTeamExamineMapper;
import com.sicnu.examine.mapper.RoleMapper;
import com.sicnu.examine.pojo.AwardTeamMap;
import com.sicnu.examine.service.AwardTeamExamineService;
import org.springframework.stereotype.Service;
import com.sicnu.examine.util.Result;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AwardTeamExamineServiceImpl implements AwardTeamExamineService {
    @Resource
    AwardTeamExamineMapper awardTeamExamineMapper;
    @Resource
    RoleMapper roleMapper;
    private Result rs;
    @Override
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
