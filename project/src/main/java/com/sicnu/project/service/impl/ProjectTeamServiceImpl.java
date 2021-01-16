package com.sicnu.project.service.impl;

import com.alibaba.fastjson.JSON;

import com.sicnu.project.mapper.ProjectTeamMapper;
import com.sicnu.project.mapper.RoleMapper;
import com.sicnu.project.mapper.UserMapper;
import com.sicnu.project.pojo.ProjectTeam;
import com.sicnu.project.pojo.ProjectTeamMap;
import com.sicnu.project.service.ProjectTeamService;
import com.sicnu.project.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目组
 */
@Service
public class ProjectTeamServiceImpl implements ProjectTeamService {

    @Resource
    ProjectTeamMapper projectTeamMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    private Result rs = null;
    @Override
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
