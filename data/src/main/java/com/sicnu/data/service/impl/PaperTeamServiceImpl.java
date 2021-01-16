package com.sicnu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.data.PaperTeamMapper;
import com.sicnu.data.RoleMapper;
import com.sicnu.data.UserMapper;
import com.sicnu.data.PaperTeam;
import com.sicnu.data.PaperTeamMap;
import com.sicnu.data.PaperTeamService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PaperTeamServiceImpl implements PaperTeamService {
    @Resource
    PaperTeamMapper paperTeamMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserMapper userMapper;
    private Result rs;

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
