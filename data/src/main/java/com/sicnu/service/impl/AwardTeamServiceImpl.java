package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.AwardTeamMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.AwardTeam;
import com.sicnu.pojo.teamMap.AwardTeamMap;
import com.sicnu.service.AwardTeamService;
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
@RequestMapping("/awardTeam")
public class AwardTeamServiceImpl implements AwardTeamService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    AwardTeamMapper awardTeamMapper;
    @Resource
    UserMapper userMapper;

    private Result rs;
    @RequestMapping("/addAwardTeamUser")
    @Override
    public Result addAwardTeamUser(Integer award_id, Integer user_id,  Integer contribution) {
        try {
                awardTeamMapper.addAwardTeamUser(award_id, user_id, contribution);
                rs = new Result("200","添加成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @RequestMapping("/delAwardTeamUser")
    @Override
    public Result delAwardTeamUser(Integer award_id,Integer user_id) {
        try {
            awardTeamMapper.delAwardTeamUser(award_id,user_id);
            rs = new Result("200","添加成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }
    @RequestMapping("/selectAwardTeam")
    @Override
    public Result selectAwardTeam(Integer award_id) {
        try {
            List<AwardTeamMap> awardTeamMaps = awardTeamMapper.selectAwardTeam(award_id);
            List<Map> mapList = new ArrayList<Map>();
            for(int i = 0; i < awardTeamMaps.size();i++) {
                Map map = JSON.parseObject(JSON.toJSONString(awardTeamMaps.get(i)), Map.class);
                map.put("role_name", roleMapper.selectRoleName(awardTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @RequestMapping("/updateAwardTeamUser")
    @Override
    public Result updateAwardTeamUser(AwardTeam awardTeam) {
        try {
            awardTeamMapper.updateAwardTeamUser(awardTeam);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
