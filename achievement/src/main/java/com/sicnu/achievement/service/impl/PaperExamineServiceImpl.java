package com.sicnu.achievement.service.impl;


import com.alibaba.fastjson.JSON;
import com.sicnu.achievement.mapper.PaperExamineMapper;
import com.sicnu.achievement.mapper.PaperTeamExamineMapper;
import com.sicnu.achievement.mapper.PeriodicalPaperExamineMapper;
import com.sicnu.achievement.mapper.UserMapper;
import com.sicnu.achievement.pojo.User;
import com.sicnu.achievement.pojo.PaperExamine;
import com.sicnu.achievement.pojo.PaperTeamMap;
import com.sicnu.achievement.service.PaperExamineService;
import com.sicnu.achievement.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 论文录入审核
 */
@Service
public class PaperExamineServiceImpl implements PaperExamineService {

    @Resource
    PaperExamineMapper paperExamineMapper;

    private Result rs;



    @Resource
    PaperTeamExamineMapper paperTeamExamineMapper;

    @Resource
    PeriodicalPaperExamineMapper periodicalPaperExamineMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    private HttpSession session;

    /**
     * 提交论文审核
     * @param paperExamine
     * @param user_id
     * @param contribution
     * @param periodicalIds
     * @return
     */
    @Override
    public Result addPaperExamine(PaperExamine paperExamine, Integer[] user_id, Integer[] contribution, Integer[] periodicalIds) {
        try {
            //判断重复提交审核
            Integer pe_id = paperExamineMapper.selectPaperExamineId(paperExamine.getLeader_id(),paperExamine.getPaper_name());
            if (pe_id!=null){
                rs = new Result("401","切勿重复提交审核",null);
                return rs;
            }
            paperExamine.setExamine_status("未审核");
            paperExamine.setApply_time(new Date());
            paperExamine.setReviewer_id(1);
            paperExamineMapper.addPaperExamine(paperExamine);
            Integer paper_id = paperExamineMapper.selectPaperExamineId(paperExamine.getLeader_id(),paperExamine.getPaper_name());
            for (int i = 0; i < user_id.length; i++) {
                paperTeamExamineMapper.addPaperTeamExamineUser(paper_id,user_id[i],contribution[i]);
            }
            for (int i = 0; i < periodicalIds.length; i++) {
                periodicalPaperExamineMapper.addPeriodicalPaperExamine(paper_id,periodicalIds[i]);
            }
            rs = new Result("200","您的论文已经上传审核，请您等待审核结果",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询
     * @param paperExamine
     * @param publish_time_start
     * @param publish_time_end
     * @param pageNum
     * @param pageSize
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    @Override
    public Result selectPaperExamineByCondition(PaperExamine paperExamine, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();


            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
            //获取登录用户的id
            Integer uid = user.getUser_id();

            //封装条件
            map.put("paper_name", paperExamine.getPaper_name());
            map.put("pt_id", paperExamine.getPt_id());
            map.put("leader_id", uid);
            map.put("periodical_id", paperExamine.getPeriodical_id());
            map.put("include_number", paperExamine.getInclude_number());
            map.put("sc_id", paperExamine.getSc_id());
            map.put("subject_id", paperExamine.getSubject_id());
            map.put("aod_id", paperExamine.getAod_id());
            map.put("sd_id", paperExamine.getSd_id());
            map.put("remark", paperExamine.getRemark());
            map.put("examine_status",paperExamine.getExamine_status());
            map.put("reviewer_id",paperExamine.getReviewer_id());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            //设置时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println("map:"+ map);
            //封装查询到的信息
            List<PaperExamine> paperExamines = paperExamineMapper.selectPaperExamineByCondition(map);

            Integer total = paperExamineMapper.selectTotalPaperExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            for(PaperExamine pe : paperExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(pe), Map.class);
                temp.put("authorName", userMapper.findUserById(pe.getLeader_id()).getUser_name());
                temp.put("publish_time", sdf.format(pe.getPublish_time()));
                temp.put("apply_time", sdf.format(pe.getApply_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 根据id删除待审核论文
     * @param pe_id
     * @return
     */
    @Override
    public Result delPaperExamineById(Integer pe_id) {
        try {
            paperExamineMapper.delPaperExamineById(pe_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据id查询待审核paper信息
     * @param pe_id
     * @return
     */
    @Override
    public Result findPaperExamineById(Integer pe_id) {
        try {
            PaperExamine paperExamine = paperExamineMapper.findPaperExamineById(pe_id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            User user = (User) session.getAttribute("user");
            Map map = JSON.parseObject(JSON.toJSONString(paperExamine), Map.class);
            map.put("user_name", user.getUser_name());
            map.put("publish_time", sdf.format(paperExamine.getPublish_time()));
            map.put("apply_time", sdf.format(paperExamine.getApply_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查看符合条件的待审核论文
     * @param paperExamine
     * @param publish_time_start
     * @param publish_time_end
     * @param pageNum
     * @param pageSize
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    public Result selectAllPaperExamineByCondition(PaperExamine paperExamine, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            //封装查询条件
            map.put("paper_name", paperExamine.getPaper_name());
            map.put("pt_id", paperExamine.getPt_id());
            map.put("periodical_id", paperExamine.getPeriodical_id());
            map.put("publish_time", paperExamine.getPublish_time());
            map.put("include_number", paperExamine.getInclude_number());
            map.put("sc_id", paperExamine.getSc_id());
            map.put("subject_id", paperExamine.getSubject_id());
            map.put("aod_id", paperExamine.getAod_id());
            map.put("sd_id", paperExamine.getSd_id());
            map.put("remark", paperExamine.getRemark());
            map.put("examine_status",paperExamine.getExamine_status());
            map.put("reviewer_id",paperExamine.getReviewer_id());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            //设置时间格式，string转化为date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println("map:"+ map);
            List<PaperExamine> paperExamines = paperExamineMapper.selectPaperExamineByCondition(map);
            //封装查询结果
            Integer total = paperExamineMapper.selectTotalPaperExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            for(PaperExamine pe : paperExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(pe), Map.class);
                temp.put("authorName", userMapper.findUserById(pe.getLeader_id()).getUser_name());
                temp.put("publish_time", sdf.format(pe.getPublish_time()));
                temp.put("apply_time", sdf.format(pe.getApply_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 查询个人待审核论文的所有信息
     * @param pe_id
     * @return
     */

    public Result findPersonalPaperExamineMessage(Integer pe_id){
        try {
            List<PaperTeamMap> paperTeamMaps = paperTeamExamineMapper.selectPaperTeamExamineUser(pe_id);
            PaperExamine paperExamine = paperExamineMapper.findPaperExamineById(pe_id);
            List<Integer> periodicalIds = periodicalPaperExamineMapper.findPeriodicalExamineByPaperId(pe_id);
            Map<String,Object> map = new HashMap<>();
            map.put("paperExamine",paperExamine);
            map.put("paperTeamMaps",paperTeamMaps);
            map.put("periodicalIds",periodicalIds);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
