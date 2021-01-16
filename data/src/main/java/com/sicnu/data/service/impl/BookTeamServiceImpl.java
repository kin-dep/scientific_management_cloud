package com.sicnu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.data.BookTeamMapper;
import com.sicnu.data.RoleMapper;
import com.sicnu.data.UserMapper;
import com.sicnu.data.BookTeam;
import com.sicnu.data.BookTeamMap;
import com.sicnu.data.BookTeamService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookTeamServiceImpl implements BookTeamService {

    @Resource
    RoleMapper roleMapper;

    @Resource
    BookTeamMapper bookTeamMapper;

    @Resource
    UserMapper userMapper;

    private Result rs ;
    @Override
    public Result addBookTeamUser(Integer book_id, Integer user_id,  Integer contribution) {
        try {
            bookTeamMapper.addBookTeamUser(book_id, user_id,contribution);
            rs = new Result("200","添加成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delBookTeamUser(Integer book_id, Integer user_id) {
        try {
            bookTeamMapper.delBookTeamUser(book_id,user_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectBookTeam(Integer book_id) {
        try {
            List<BookTeamMap> bookTeamMaps = bookTeamMapper.selectBookTeam(book_id);
            List<Map> mapList = new ArrayList<Map>();
            for(int i = 0; i < bookTeamMaps.size(); i++) {
                Map map = JSON.parseObject(JSON.toJSONString(bookTeamMaps.get(i)), Map.class);
                map.put("role_name", roleMapper.selectRoleName(bookTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateBookTeamUser(BookTeam bookTeam) {
        try {
            bookTeamMapper.updateBookTeamUser(bookTeam);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args) {
        Date date = new Date();//当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
//        end_time = sdf.format(calendar.getTime());
        System.out.println(sdf.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, -6);//月份减6
        String start_time;
        start_time = sdf.format(calendar.getTime());
        System.out.println(sdf.format(calendar.getTime()));//输出格式化的日期
        String[] str = start_time.split("-");
        System.out.println(str[0]+"   "+str[1]);
        int a = Integer.parseInt(str[1]);
        System.out.println(a);
        String checkTime;
        if (a==6){
            checkTime = str[0] + "年第一学期";
        }else{
            checkTime = str[0] + "年第二学期";

        }
        System.out.println(checkTime);
    }
}
