package com.sicnu.examine.service.impl;


import com.alibaba.fastjson.JSON;
import com.sicnu.examine.mapper.AwardExamineMapper;
import com.sicnu.examine.mapper.AwardTeamExamineMapper;
import com.sicnu.examine.mapper.UserMapper;
import com.sicnu.examine.pojo.AwardExamine;
import com.sicnu.examine.pojo.AwardTeamMap;
import com.sicnu.examine.pojo.User;
import com.sicnu.examine.service.AwardExamineService;
import org.springframework.stereotype.Service;
import com.sicnu.examine.util.Result;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AwardExamineServiceImpl implements AwardExamineService {
    @Resource
    AwardExamineMapper awardExamineMapper;

    @Resource
    AwardTeamExamineMapper awardTeamExamineMapper;

    private Result rs;



    @Resource
    private HttpSession session;
    @Resource
    UserMapper userMapper;

    /**
     * 获奖成果提交审核
     * @param awardExamine 获奖信息
     * @param user_id 添加的组员的id
     * @param contribution 组员的贡献
     * @return
     */
    @Override
    public Result addAwardExamine(AwardExamine awardExamine, Integer[] user_id, Integer[] contribution) {
        try {
            //通过部分信息获取id
            Integer award_id1 = awardExamineMapper.selectAwardExamineId(awardExamine.getAward_name(),awardExamine.getLeader_id());
            //判断是否已经存在
            if(award_id1 !=null){
                rs = new Result("401","切勿重复提交审核",null);
                return rs;
            }
            //默认未审核
            awardExamine.setExamine_status("未审核");
            awardExamine.setApply_time(new Date());
            awardExamine.setReviewer_id(1);
            //添加审核获奖成果
            awardExamineMapper.addAwardExamine(awardExamine);
            Integer award_id = awardExamineMapper.selectAwardExamineId(awardExamine.getAward_name(),awardExamine.getLeader_id());
            System.out.println(award_id);
            System.out.println(Arrays.toString(user_id));
            //添加组员
            for (int i = 0; i < user_id.length; i++) {
                System.out.println(i+" "+user_id[i]);
                awardTeamExamineMapper.addAwardExamineTeamUser(award_id,user_id[i],contribution[i]);
            }
            rs = new Result("200","奖励已经录入系统审核,请您耐心等待",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查看个人待审核项目
     * @param awardExamine
     * @param award_time_start
     * @param award_time_end
     * @param pageSize
     * @param pageNum
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    @Override
    public Result selectAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
//            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();
            //把条件封装到map里面，xml文件接收为map形式
            map.put("achievement_name", awardExamine.getAchievement_name());
            map.put("leader_id", uid);
            map.put("award_name", awardExamine.getAward_name());
            map.put("issuing_authority", awardExamine.getIssuing_authority());
            map.put("approval_number", awardExamine.getApproval_number());
            map.put("aod_id", awardExamine.getAod_id());
            map.put("dr_id", awardExamine.getDr_id());
            map.put("ar_id", awardExamine.getAr_id());
            map.put("al_id", awardExamine.getAl_id());
            map.put("sc_id", awardExamine.getSc_id());
            map.put("subject_id", awardExamine.getSubject_id());
            map.put("sd_id", awardExamine.getSd_id());
            map.put("at_id", awardExamine.getAt_id());
            map.put("examine_status",awardExamine.getExamine_status());
            map.put("reviewer_id",awardExamine.getReviewer_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            //设置时间格式，并且把string转换未date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!award_time_start.equals("")) {
                map.put("award_time_start", sdf.parse(award_time_start));
            }
            if (!award_time_end.equals("")) {
                map.put("award_time_end", sdf.parse(award_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            List<AwardExamine> awardExamines = awardExamineMapper.selectAwardExamineByCondition(map);
            Integer total = awardExamineMapper.selectTotalAwardExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            for(AwardExamine ae : awardExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(ae), Map.class);
                temp.put("authorName", userMapper.findUserById(ae.getLeader_id()).getUser_name());
                temp.put("award_time", sdf.format(ae.getAward_time()));
                temp.put("apply_time", sdf.format(ae.getApply_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 根据ae_id 删除待审核项目
     * @param ae_id
     * @return
     */

    @Override
    public Result delAwardExamineById(Integer ae_id) {
        awardExamineMapper.delAwardExamineById(ae_id);
        rs = new Result("200","删除成功",null);
        return rs;
    }

    /**
     * 查看某个待审核获奖成果的详细信息
     * @param ae_id
     * @return
     */

    @Override
    public Result findAwardExamineById(Integer ae_id) {
        try {


            User user = (User) session.getAttribute("user");
            AwardExamine awardExamine = awardExamineMapper.findAwardExamineById(ae_id);
            Map map = JSON.parseObject(JSON.toJSONString(awardExamine), Map.class);
            map.put("user_name", user.getUser_name());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            map.put("apply_time", sdf.format(awardExamine.getApply_time()));
            map.put("award_time", sdf.format(awardExamine.getAward_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查看所有待审核获奖成果
     * @param awardExamine
     * @param award_time_start
     * @param award_time_end
     * @param pageSize
     * @param pageNum
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    public Result selectAllAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            //把条件封装到map里面，xml文件接收为map形式
            map.put("achievement_name", awardExamine.getAchievement_name());
            map.put("award_name", awardExamine.getAward_name());
            map.put("issuing_authority", awardExamine.getIssuing_authority());
            map.put("approval_number", awardExamine.getApproval_number());
            map.put("aod_id", awardExamine.getAod_id());
            map.put("dr_id", awardExamine.getDr_id());
            map.put("ar_id", awardExamine.getAr_id());
            map.put("al_id", awardExamine.getAl_id());
            map.put("sc_id", awardExamine.getSc_id());
            map.put("subject_id", awardExamine.getSubject_id());
            map.put("sd_id", awardExamine.getSd_id());
            map.put("at_id", awardExamine.getAt_id());
            map.put("examine_status",awardExamine.getExamine_status());
            map.put("reviewer_id",awardExamine.getReviewer_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            //设置时间格式，并且把string转换未date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!award_time_start.equals("")) {
                map.put("award_time_start", sdf.parse(award_time_start));
            }
            if (!award_time_end.equals("")) {
                map.put("award_time_end", sdf.parse(award_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            //根据条件查找
            List<AwardExamine> awardExamines = awardExamineMapper.selectAwardExamineByCondition(map);
            //根据条件查找出来的数量
            Integer total = awardExamineMapper.selectTotalAwardExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            //查看项目组员信息
            for(AwardExamine ae : awardExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(ae), Map.class);
                temp.put("authorName", userMapper.findUserById(ae.getLeader_id()).getUser_name());
                temp.put("award_time", sdf.format(ae.getAward_time()));
                temp.put("apply_time", sdf.format(ae.getApply_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 获取个人所有待审核获奖成果
     * @param ae_id
     * @return
     */
    public Result findPersonalAwardExamineMessage(Integer ae_id){
        try {
            List<AwardTeamMap> awardTeamMaps = awardTeamExamineMapper.selectAwardTeamExamineUser(ae_id);
            AwardExamine awardExamine = awardExamineMapper.findAwardExamineById(ae_id);
            Map<String,Object> map = new HashMap<>();
            map.put("awardExamine",awardExamine);
            map.put("awardTeamMaps",awardTeamMaps);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}
