package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.PaperTeamExamineMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.service.PaperTeamExamineService;
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
@RequestMapping("/paperTeamExamine")
public class PaperTeamExamineServiceImpl implements PaperTeamExamineService {
    @Resource
    PaperTeamExamineMapper paperTeamExamineMapper;

    @Resource
    RoleMapper roleMapper;

    private Result rs;
    @RequestMapping("/selectPaperTeamExamineUser")
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
