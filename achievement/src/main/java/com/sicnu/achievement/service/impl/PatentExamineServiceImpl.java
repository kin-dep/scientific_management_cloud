package com.sicnu.achievement.service.impl;


import com.alibaba.fastjson.JSON;
import com.sicnu.achievement.mapper.PatentExamineMapper;
import com.sicnu.achievement.mapper.PatentTeamExamineMapper;
import com.sicnu.achievement.mapper.UserMapper;
import com.sicnu.achievement.pojo.User;
import com.sicnu.achievement.pojo.PatentExamine;
import com.sicnu.achievement.pojo.PatentTeamMap;
import com.sicnu.achievement.service.PatentExamineService;
import com.sicnu.achievement.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专利成果录入审核
 */
@Service
public class PatentExamineServiceImpl implements PatentExamineService {

    @Resource
    PatentExamineMapper patentExamineMapper;

    private Result rs;
    @Resource
    PatentTeamExamineMapper patentTeamExamineMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    private HttpSession session;

    /**
     * 录入专利成果
     * @param patentExamine
     * @param user_id
     * @param contribution
     * @return
     */
    @Override
    public Result addPatentExamine(PatentExamine patentExamine, Integer[] user_id, Integer[] contribution) {
        try {
            //通过部分信息获取id
            Integer pe_id1 = patentExamineMapper.selectPatentExamineId(patentExamine.getLeader_id(),patentExamine.getPatent_name());
            //判断是否已经存在
            if(pe_id1 !=null){
                rs = new Result("401","切勿重复提交审核",null);
                return rs;
            }
            //默认未审核
            patentExamine.setExamine_status("未审核");
            patentExamine.setApply_time(new Date());
            patentExamine.setReviewer_id(1);
            patentExamineMapper.addPatentExamine(patentExamine);
            Integer patent_id = patentExamineMapper.selectPatentExamineId(patentExamine.getLeader_id(),patentExamine.getPatent_name());
            for (int i = 0; i < user_id.length; i++) {
                patentTeamExamineMapper.addPatentTeamExamineUser(patent_id,user_id[i],contribution[i]);
            }
            rs =new Result("200","您的专利已经上传审核，请您耐心等待审核结果",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询待审核项目
     * @param patentExamine
     * @param application_time_start
     * @param application_time_end
     * @param public_time_start
     * @param public_time_end
     * @param authorization_time_start
     * @param authorization_time_end
     * @param pageSize
     * @param pageNum
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    @Override
    public Result selectPatentExamineByCondition(PatentExamine patentExamine, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
//            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();

            map.put("patent_name", patentExamine.getPatent_name());
            map.put("leader_id", uid);
            map.put("pt_id", patentExamine.getPt_id());
            map.put("pr_id", patentExamine.getPr_id());
            map.put("ps_id", patentExamine.getPs_id());
            map.put("aod_id", patentExamine.getAod_id());
            map.put("application_number", patentExamine.getApplication_number());
            map.put("public_number", patentExamine.getPublic_number());
            map.put("authorization_number", patentExamine.getAuthorization_number());
            map.put("examine_status",patentExamine.getExamine_status());
            map.put("reviewer_id",patentExamine.getReviewer_id());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!application_time_start.equals("")) {
                map.put("application_time_start", sdf.parse(application_time_start));
            }
            if (!application_time_end.equals("")) {
                map.put("application_time_end", sdf.parse(application_time_end));
            }
            if (!public_time_start.equals("")) {
                map.put("public_time_start", sdf.parse(public_time_start));
            }
            if (!public_time_end.equals("")) {
                map.put("public_time_end", sdf.parse(public_time_end));
            }
            if (!authorization_time_start.equals("")) {
                map.put("authorization_time_start", sdf.parse(authorization_time_start));
            }
            if (!authorization_time_end.equals("")) {
                map.put("authorization_time_end", sdf.parse(authorization_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            Integer total = patentExamineMapper.selectTotalPatentExamine(map);
            List<PatentExamine> patentExamines = patentExamineMapper.selectPatentExamineByCondition(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            for(PatentExamine pe : patentExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(pe), Map.class);
                temp.put("authorName", userMapper.findUserById(pe.getLeader_id()).getUser_name());
                temp.put("application_time", sdf.format(pe.getApplication_time()));
                temp.put("public_time", sdf.format(pe.getPublic_time()));
                temp.put("authorization_time", sdf.format(pe.getAuthorization_time()));
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
     * 删除专利成果
     * @param pe_id
     * @return
     */
    @Override
    public Result delPatentExamineById(Integer pe_id) {
        try {
            patentExamineMapper.delPatentExamineById(pe_id);
            rs =new Result("200","删除成功",null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据id获取专利成果信息
     * @param pe_id
     * @return
     */
    @Override
    public Result findPatentExamineById(Integer pe_id) {
        try {
            PatentExamine patentExamine = patentExamineMapper.findPatentExamineById(pe_id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            User user = (User) session.getAttribute("user");
            Map map = JSON.parseObject(JSON.toJSONString(patentExamine), Map.class);
            map.put("user_name", user.getUser_name());
            map.put("application_time", sdf.format(patentExamine.getApplication_time()));
            map.put("authorization_time", sdf.format(patentExamine.getAuthorization_time()));
            map.put("public_time", sdf.format(patentExamine.getPublic_time()));
            map.put("apply_time", sdf.format(patentExamine.getApply_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询所有符合条件的待审核项目
     * @param patentExamine
     * @param application_time_start
     * @param application_time_end
     * @param public_time_start
     * @param public_time_end
     * @param authorization_time_start
     * @param authorization_time_end
     * @param pageSize
     * @param pageNum
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    public Result selectAllPatentExamineByCondition(PatentExamine patentExamine, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();
            //封装查询条件
            map.put("patent_name", patentExamine.getPatent_name());
            map.put("pt_id", patentExamine.getPt_id());
            map.put("pr_id", patentExamine.getPr_id());
            map.put("ps_id", patentExamine.getPs_id());
            map.put("aod_id", patentExamine.getAod_id());
            map.put("application_number", patentExamine.getApplication_number());
            map.put("public_number", patentExamine.getPublic_number());
            map.put("authorization_number", patentExamine.getAuthorization_number());
            map.put("examine_status",patentExamine.getExamine_status());
            map.put("reviewer_id",patentExamine.getReviewer_id());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            //设置日期格式，把前端传来的string日期转化未date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!application_time_start.equals("")) {
                map.put("application_time_start", sdf.parse(application_time_start));
            }
            if (!application_time_end.equals("")) {
                map.put("application_time_end", sdf.parse(application_time_end));
            }
            if (!public_time_start.equals("")) {
                map.put("public_time_start", sdf.parse(public_time_start));
            }
            if (!public_time_end.equals("")) {
                map.put("public_time_end", sdf.parse(public_time_end));
            }
            if (!authorization_time_start.equals("")) {
                map.put("authorization_time_start", sdf.parse(authorization_time_start));
            }
            if (!authorization_time_end.equals("")) {
                map.put("authorization_time_end", sdf.parse(authorization_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            //封装查询结果
            Integer total = patentExamineMapper.selectTotalPatentExamine(map);
            List<PatentExamine> patentExamines = patentExamineMapper.selectPatentExamineByCondition(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            for(PatentExamine pe : patentExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(pe), Map.class);
                temp.put("authorName", userMapper.findUserById(pe.getLeader_id()).getUser_name());
                temp.put("application_time", sdf.format(pe.getApplication_time()));
                temp.put("public_time", sdf.format(pe.getPublic_time()));
                temp.put("authorization_time", sdf.format(pe.getAuthorization_time()));
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
     * 查询某个人专利成果的个人信息
     * @param pe_id
     * @return
     */
    public Result findPersonalPatentExamineMessage(Integer pe_id){
        try {
            List<PatentTeamMap> patentTeamMaps = patentTeamExamineMapper.selectPatentTeamExamineUser(pe_id);
            PatentExamine patentExamine = patentExamineMapper.findPatentExamineById(pe_id);
            Map<String,Object> map = new HashMap<>();
            map.put("patentTeamMaps",patentTeamMaps);
            map.put("patentExamine",patentExamine);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
