package com.sicnu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.data.AwardTeamMapper;
import com.sicnu.data.RoleMapper;
import com.sicnu.data.UserMapper;
import com.sicnu.data.AwardTeam;
import com.sicnu.data.AwardTeamMap;
import com.sicnu.data.AwardTeamService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AwardTeamServiceImpl implements AwardTeamService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    AwardTeamMapper awardTeamMapper;
    @Resource
    UserMapper userMapper;

    private Result rs;
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
