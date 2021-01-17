package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.ProjectTeamMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.ProjectTeam;
import com.sicnu.pojo.teamMap.ProjectTeamMap;
import com.sicnu.service.ProjectTeamService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目组
 */
@Service
@RestController
@RequestMapping("/projectTeam")
public class ProjectTeamServiceImpl implements ProjectTeamService {

    @Resource
    ProjectTeamMapper projectTeamMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    private Result rs = null;
    @Override
    @RequestMapping("/addProjectTeamUser")

    public Result addProjectTeamUser(Integer project_id, Integer user_id, String team_role) {
        try {
                projectTeamMapper.addProjectTeamUser(project_id, user_id,team_role);
                rs = new Result("200", "成员添加成功", null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @RequestMapping("/delProjectTeamUser")

    public Result delProjectTeamUser(Integer project_id,Integer user_id) {
        try {
            projectTeamMapper.delProjectTeamUser(project_id,user_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @RequestMapping("/selectProjectTeam")

    public Result selectProjectTeam(Integer project_id) {
        try {
            List<ProjectTeamMap> projectTeamMaps = projectTeamMapper.selectProjectTeam(project_id);
            List<Map> mapList = new ArrayList<>();
            for(ProjectTeamMap ptm : projectTeamMaps) {
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

    @Override
    @RequestMapping("/updateProjectTeamUser")

    public Result updateProjectTeamUser(ProjectTeam projectTeam) {
        try {
            projectTeamMapper.updateProjectTeamUser(projectTeam);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
