package com.sicnu.achievement.service.impl;


import com.alibaba.fastjson.JSON;
import com.sicnu.achievement.mapper.BookTeamExamineMapper;
import com.sicnu.achievement.mapper.RoleMapper;
import com.sicnu.achievement.pojo.BookTeamMap;
import com.sicnu.achievement.service.BookTeamExamineService;
import com.sicnu.achievement.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookTeamExamineServiceImpl implements BookTeamExamineService {
    @Resource
    BookTeamExamineMapper bookTeamExamineMapper;
    @Resource
    RoleMapper roleMapper;
    private Result rs;

    @Override
    public Result selectBookTeamExamineUser(Integer be_id) {
        try {
            List<BookTeamMap> bookTeamMaps = bookTeamExamineMapper.selectBookTeamExamineUser(be_id);
            List<Map> mapList = new ArrayList<>();
            for(BookTeamMap btm : bookTeamMaps) {
                Map map = JSON.parseObject(JSON.toJSONString(btm), Map.class);
                map.put("role_name", roleMapper.selectRoleName(btm.getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
