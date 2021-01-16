package com.sicnu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.data.ProjectTeamExamineMapper;
import com.sicnu.data.RoleMapper;
import com.sicnu.data.ProjectTeamMap;
import com.sicnu.data.ProjectTeamExamineService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectTeamExamineServiceImpl implements ProjectTeamExamineService {
    @Resource
    ProjectTeamExamineMapper projectTeamExamineMapper;
    @Resource
    RoleMapper roleMapper;

    private Result rs;

    @Override
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
