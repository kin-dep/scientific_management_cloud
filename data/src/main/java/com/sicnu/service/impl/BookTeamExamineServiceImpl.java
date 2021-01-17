package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.BookTeamExamineMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.service.BookTeamExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Service
@RequestMapping("/bookTeamExamine")
public class BookTeamExamineServiceImpl implements BookTeamExamineService {
    @Resource
    BookTeamExamineMapper bookTeamExamineMapper;
    @Resource
    RoleMapper roleMapper;
    private Result rs;
    @RequestMapping("/selectBookTeamExamineUser")
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
