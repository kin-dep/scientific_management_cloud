package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.PatentTeamMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.PatentTeam;
import com.sicnu.pojo.teamMap.PatentTeamMap;
import com.sicnu.service.PatentTeamService;
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
@RequestMapping("/patentTeam")
public class PatentTeamServiceImpl implements PatentTeamService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    PatentTeamMapper patentTeamMapper;

    @Resource
    UserMapper userMapper;
    private Result rs;
    @Override
    @RequestMapping("/addPatentTeamUser")

    public Result addPatentTeamUser(Integer patent_id, Integer user_id, Integer contribution) {
        try {
            patentTeamMapper.addPatentTeamUser(patent_id, user_id, contribution);
            rs = new Result("200","添加成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @RequestMapping("/delPatentTeamUser")

    public Result delPatentTeamUser(Integer patent_id, Integer user_id) {
        try {
            patentTeamMapper.delPatentTeamUser(patent_id,user_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @RequestMapping("/selectPatentTeam")

    public Result selectPatentTeam(Integer patent_id) {
        try {
            List<PatentTeamMap> patentTeamMaps= patentTeamMapper.selectPatentTeam(patent_id);
            List<Map> mapList = new ArrayList<>();
            for(int i = 0; i < patentTeamMaps.size(); i++) {
                Map map = JSON.parseObject(JSON.toJSONString(patentTeamMaps.get(i)), Map.class);
                map.put("role_name", roleMapper.selectRoleName(patentTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @RequestMapping("/updatePatentTeamUser")

    public Result updatePatentTeamUser(PatentTeam patentTeam) {
        try {
            patentTeamMapper.updatePatentTeamUser(patentTeam);
            rs =new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
