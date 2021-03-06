package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.PaperTeamMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.PaperTeam;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.service.PaperTeamService;
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
@RequestMapping("/paperTeam")
public class PaperTeamServiceImpl implements PaperTeamService {
    @Resource
    PaperTeamMapper paperTeamMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserMapper userMapper;
    private Result rs;
    @RequestMapping("/addPaperTeamUser")
    @Override
    public Result addPaperTeamUser(Integer paper_id,Integer user_id,Integer contribution) {
        try {


            paperTeamMapper.addPaperTeamUser(paper_id, user_id, contribution);
            rs = new Result("200","添加作者成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @RequestMapping("/delPaperTeamUser")
    @Override
    public Result delPaperTeamUser(Integer paper_id,Integer user_id) {
        try {
            paperTeamMapper.delPaperTeamUser(paper_id,user_id);
            rs = new Result("200","删除作者成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @RequestMapping("/selectPaperTeam")
    @Override
    public Result selectPaperTeam(Integer paper_id) {
        try {
            List<PaperTeamMap> paperTeamMaps = paperTeamMapper.selectPaperTeam(paper_id);
            List<Map> mapList = new ArrayList<Map>();
            for(int i = 0; i < paperTeamMaps.size(); i++) {
                Map map = JSON.parseObject(JSON.toJSONString(paperTeamMaps.get(i)), Map.class);
                map.put("role_name", roleMapper.selectRoleName(paperTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @RequestMapping("/updatePaperTeamUser")
    @Override
    public Result updatePaperTeamUser(PaperTeam paperTeam) {
        try {
            paperTeamMapper.updatePaperTeamUser(paperTeam);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
