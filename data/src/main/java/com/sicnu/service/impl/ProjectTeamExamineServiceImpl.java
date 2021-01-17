package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.ProjectTeamExamineMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.teamMap.ProjectTeamMap;
import com.sicnu.service.ProjectTeamExamineService;
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
@RequestMapping("/projectTeamExamine")
public class ProjectTeamExamineServiceImpl implements ProjectTeamExamineService {
    @Resource
    ProjectTeamExamineMapper projectTeamExamineMapper;
    @Resource
    RoleMapper roleMapper;

    private Result rs;

    @Override
    @RequestMapping("/selectProjectTeamExamineUser")

    public Result selectProjectTeamExamineUser(Integer pe_id) {
        List<ProjectTeamMap> projectTeamMaps = projectTeamExamineMapper.selectProjectTeamExamineUser(pe_id);
        List<Map> mapList = new ArrayList<>();
        for(ProjectTeamMap ptm : projectTeamMaps) {
            Map map = JSON.parseObject(JSON.toJSONString(ptm), Map.class);
            map.put("role_name", roleMapper.selectRoleName(ptm.getRole_id()));
            mapList.add(map);
        }
        rs = new Result("200",null, mapList);
        return rs;
    }
}
