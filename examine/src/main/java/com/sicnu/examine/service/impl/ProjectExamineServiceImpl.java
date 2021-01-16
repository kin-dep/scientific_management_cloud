package com.sicnu.examine.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.examine.mapper.ProjectExamineMapper;
import com.sicnu.examine.mapper.ProjectTeamExamineMapper;
import com.sicnu.examine.mapper.UserMapper;
import com.sicnu.examine.pojo.ProjectExamine;
import com.sicnu.examine.pojo.ProjectTeamMap;
import com.sicnu.examine.pojo.User;
import com.sicnu.examine.service.ProjectExamineService;
import com.sicnu.examine.util.Result;
import org.springframework.stereotype.Service;
import com.sicnu.examine.util.Result;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectExamineServiceImpl implements ProjectExamineService {
    @Resource
    ProjectExamineMapper projectExamineMapper;

    private Result rs;
    @Resource
    ProjectTeamExamineMapper projectTeamExamineMapper;

    @Resource
    private HttpSession session;
    @Resource
    UserMapper userMapper;

    /**
     * 项目提交审核
     * @param projectExamine
     * @param user_id
     * @param team_role
     * @return
     */
    @Override
    public Result addProjectExamine(ProjectExamine projectExamine, Integer[] user_id, String []team_role) {
        try {
            projectExamine.setExamine_status("未审核");
            projectExamine.setApply_time(new Date());
            projectExamine.setReviewer_id(1);
            projectExamineMapper.addProjectExamine(projectExamine);
            Integer project_id = projectExamineMapper.selectProjectExamineId(projectExamine.getLeader_id(),projectExamine.getProject_name());
            for (int i = 0; i < user_id.length; i++) {
                projectTeamExamineMapper.addProjectTeamExamineUser(project_id,user_id[i],team_role[i]);
            }
            rs = new Result("200","您的项目已经上传审核，请您耐心等待",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询相应待审核项目
     * @param projectExamine
     * @param start_time_start
     * @param start_time_end
     * @param complete_time_start
     * @param complete_time_end
     * @param plan_time_start
     * @param plan_time_end
     * @param pageNum
     * @param pageSize
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    @Override
    public Result selectProjectExamineByCondition(ProjectExamine projectExamine, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
            //获取登录用户的id
            Integer uid = user.getUser_id();
            System.out.println(uid);
            System.out.println(projectExamine.getProject_name());

            //封装查询条件
            map.put("project_name", projectExamine.getProject_name());
            map.put("leader_id", uid);
            map.put("department_id", projectExamine.getDepartment_id());
            map.put("sc_id", projectExamine.getSc_id());
            map.put("subject_id", projectExamine.getSubject_id());
            map.put("nature_id", projectExamine.getNature_id());
            map.put("level_id", projectExamine.getLevel_id());
            map.put("status_id", projectExamine.getStatus_id());
            map.put("sd_id", projectExamine.getSd_id());
            map.put("outlay", projectExamine.getOutlay());
            map.put("approval_number", projectExamine.getApproval_number());
            map.put("ct_id", projectExamine.getCt_id());
            map.put("examine_status",projectExamine.getExamine_status());
            map.put("reviewer_id",projectExamine.getReviewer_id());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            //格式转换
            if (!start_time_start.equals("")) {
                map.put("start_time_start", sdf.parse(start_time_start));
            }
            if (!start_time_end.equals("")) {
                map.put("start_time_end", sdf.parse(start_time_end));
            }
            if (!complete_time_start.equals("")) {
                map.put("complete_time_start", sdf.parse(complete_time_start));
            }
            if (!complete_time_end.equals("")) {
                map.put("complete_time_end", sdf.parse(complete_time_end));
            }
            if (!plan_time_start.equals("")) {
                map.put("plan_time_start", sdf.parse(plan_time_start));
            }
            if (!plan_time_end.equals("")) {
                map.put("plan_time_end", sdf.parse(plan_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            //疯转结果信息
            //根据传来的条件筛选用户
            List<ProjectExamine> ProjectExamines = projectExamineMapper.selectProjectExamineByCondition(map);
            //根据条件获取的项目条数
            Integer total = projectExamineMapper.selectTotalProjectExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            //信息设置格式
            for(ProjectExamine pe : ProjectExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(pe), Map.class);
                temp.put("user_name", userMapper.findUserById(pe.getLeader_id()).getUser_name());
                temp.put("complete_time", sdf.format(pe.getComplete_time()));
                temp.put("start_time", sdf.format(pe.getStart_time()));
                temp.put("plan_time", sdf.format(pe.getPlan_time()));
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
     * 根据某个id删除某个待审核项目
     * @param pe_id
     * @return
     */
    @Override
    public Result delProjectExamine(Integer pe_id) {
        try {
            projectExamineMapper.delProjectExamineById(pe_id);
            rs = new Result("200", "删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 查询某个待审核项目全部信息
     * @param pe_id
     * @return
     */
    @Override
    public Result findProjectExamineById(Integer pe_id) {
        try {
            User user = (User) session.getAttribute("user");
            ProjectExamine projectExamine = projectExamineMapper.findProjectExamineById(pe_id);
            Map map = JSON.parseObject(JSON.toJSONString(projectExamine), Map.class);
            map.put("user_name", user.getUser_name());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            map.put("complete_time", sdf.format(projectExamine.getComplete_time()));
            map.put("start_time", sdf.format(projectExamine.getStart_time()));
            map.put("plan_time", sdf.format(projectExamine.getPlan_time()));
            map.put("apply_time", sdf.format(projectExamine.getApply_time()));

            rs = new Result("200", null, map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询相应所有待审核项目
     * @param projectExamine
     * @param start_time_start
     * @param start_time_end
     * @param complete_time_start
     * @param complete_time_end
     * @param plan_time_start
     * @param plan_time_end
     * @param pageNum
     * @param pageSize
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    public Result selectAllProjectExamineByCondition(ProjectExamine projectExamine, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println(projectExamine.getProject_name());
            map.put("project_name", projectExamine.getProject_name());
            map.put("department_id", projectExamine.getDepartment_id());
            map.put("sc_id", projectExamine.getSc_id());
            map.put("subject_id", projectExamine.getSubject_id());
            map.put("nature_id", projectExamine.getNature_id());
            map.put("level_id", projectExamine.getLevel_id());
            map.put("status_id", projectExamine.getStatus_id());
            map.put("sd_id", projectExamine.getSd_id());
            map.put("outlay", projectExamine.getOutlay());
            map.put("approval_number", projectExamine.getApproval_number());
            map.put("ct_id", projectExamine.getCt_id());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            if (!start_time_start.equals("")) {
                map.put("start_time_start", sdf.parse(start_time_start));
            }
            if (!start_time_end.equals("")) {
                map.put("start_time_end", sdf.parse(start_time_end));
            }
            if (!complete_time_start.equals("")) {
                map.put("complete_time_start", sdf.parse(complete_time_start));
            }
            if (!complete_time_end.equals("")) {
                map.put("complete_time_end", sdf.parse(complete_time_end));
            }
            if (!plan_time_start.equals("")) {
                map.put("plan_time_start", sdf.parse(plan_time_start));
            }
            if (!plan_time_end.equals("")) {
                map.put("plan_time_end", sdf.parse(plan_time_end));
            }
            System.out.println(map);
            //根据传来的条件筛选用户
            List<ProjectExamine> ProjectExamines = projectExamineMapper.selectProjectExamineByCondition(map);
            //根据条件获取的项目条数
            Integer total = projectExamineMapper.selectTotalProjectExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            for(ProjectExamine pe : ProjectExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(pe), Map.class);
                temp.put("user_name", userMapper.findUserById(pe.getLeader_id()).getUser_name());
                temp.put("complete_time", sdf.format(pe.getComplete_time()));
                temp.put("start_time", sdf.format(pe.getStart_time()));
                temp.put("plan_time", sdf.format(pe.getPlan_time()));
                temp.put("apply_time", sdf.format(pe.getApply_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return rs = new Result("200", null, list);
    }

    public Result findPersonalProjectExamineMessage(Integer pe_id){
        try {
            List<ProjectTeamMap> projectTeamMaps = projectTeamExamineMapper.selectProjectTeamExamineUser(pe_id);
            ProjectExamine projectExamine = projectExamineMapper.findProjectExamineById(pe_id);
            Map<String,Object> map = new HashMap<>();
            map.put("projectTeamMaps",projectTeamMaps);
            map.put("projectExamine",projectExamine);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
