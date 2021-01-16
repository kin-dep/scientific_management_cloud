package com.sicnu.system_management.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.system_management.mapper.AnounceMapper;
import com.sicnu.system_management.mapper.UserMapper;
import com.sicnu.system_management.pojo.Anounce;
import com.sicnu.system_management.pojo.User;
import com.sicnu.system_management.service.AnounceService;
import com.sicnu.system_management.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AnounceServiceImpl implements AnounceService {

    @Resource
    AnounceMapper anounceMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    HttpSession session;

    private Result rs;

    @Override
    public Result addAnounce(Anounce anounce) {
        try {
            User user = (User) session.getAttribute("user");
            anounce.setAnounce_author(user.getUser_id());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            anounce.setAnounce_date(sdf.format(new Date()));
            anounceMapper.addAnounce(anounce);
            rs = new Result("200", "添加公告成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delAnounceById(Integer anounce_id) {
        try {
            anounceMapper.delAnounceById(anounce_id);
            rs = new Result("200", "删除公告成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateAnounce(Anounce anounce) {
        try {
            System.err.println(anounce);
            anounceMapper.updateAnounce(anounce);
            rs = new Result("200","更新公告成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectAllAnounce(Map<String, Object> condition) {
        try {
            List<Anounce> anounces = anounceMapper.selectAllAnounce(condition);
            List<Map> mapList = new ArrayList<>();
            for(Anounce anounce : anounces) {
                Map map = JSON.parseObject(JSON.toJSONString(anounce), Map.class);
                map.put("user_name", userMapper.findUserById(anounce.getAnounce_author()).getUser_name());
                mapList.add(map);
            }
            Integer total = anounceMapper.countAnounce();
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("total", total);
            resMap.put("anounceList", mapList);
            rs = new Result("200", "查询公告列表成功", resMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectAnounceById(Integer anounce_id) {
        try {
            Anounce anounce = anounceMapper.selectAnounceById(anounce_id);
            Map map = JSON.parseObject(JSON.toJSONString(anounce), Map.class);
            map.put("user_name", userMapper.findUserById(anounce.getAnounce_author()).getUser_name());
            rs = new Result("200", "查询公告信息成功", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
