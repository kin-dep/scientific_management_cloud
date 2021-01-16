package com.sicnu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.data.*;
import com.sicnu.data.Award;
import com.sicnu.data.User;
import com.sicnu.data.AwardTeamExamine;
import com.sicnu.data.AwardTeamMap;
import com.sicnu.data.UserAuth;
import com.sicnu.data.AwardService;
import com.sicnu.data.Result;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获奖成果
 */
@Service
public class AwardServiceImpl implements AwardService {
    @Resource
    AwardMapper awardMapper;

    private Result rs;

    @Resource
    UserMapper userDao;

    @Resource
    AwardExamineMapper awardExamineMapper;
    @Resource
    AwardTeamMapper awardTeamMapper;
    @Resource
    AwardTeamExamineMapper awardTeamExamineMapper;
    @Resource
    JavaMailSenderImpl mailSender;
    @Resource
    CacheUserMapper cacheUserMapper;
    @Resource
    RoleAuthMapper roleAuthMapper;
    @Resource
    private HttpSession session;

    /**
     * 审核成果
     * @param award
     * @param checkMessage
     * @param message
     * @return
     */
    @Override
    public Result addAward(Award award,String checkMessage,String message) {
        try {

            Integer awardExamineId = awardExamineMapper.selectAwardExamineId(award.getAward_name(),award.getLeader_id());
            List<AwardTeamExamine> awardTeamExamines = awardTeamExamineMapper.selectAwardTeamExamineById(awardExamineId);

            //获取项目负责人信息
            User user = userDao.findUserById(award.getLeader_id());
            //创建邮件环境，反馈信息
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);

            //如果不通过审核反馈
            if (checkMessage.equals("fail")) {
                helper.setSubject("高校科研管理系统：申请被驳回");
                helper.setText("<p>您的项目申报审核未通过，因为<span style='color:blue;text-decoration:underline'>" + message + "</span>,请您解决之后重新申请。</p>", true);
                //负责人邮箱
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                //从待审核里面删除
                awardTeamExamineMapper.delAwardTeamExamineTeam(awardExamineId);
                awardExamineMapper.delAwardExamine(award.getLeader_id(),award.getAward_name());
                rs = new Result("200", "审核结果已反馈", null);
            } else {
                awardMapper.addAward(award);
                //获取项目id 返给用户
                Integer awardId = awardMapper.selectAwardId(award.getLeader_id(), award.getAward_name());
                helper.setSubject("高校科研管理系统：申请已通过");
                helper.setText("<p>您的项目申报审核成功，项目编号为：<span style='color:blue;text-decoration:underline'>" + awardId + "</span>,请勿遗忘。</p>", true);
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                //从待审核删除
                awardExamineMapper.delAwardExamine(award.getLeader_id(),award.getAward_name());
                //确定项目组
                for (AwardTeamExamine awardTeamExamine : awardTeamExamines) {
                    awardTeamMapper.addAwardTeamUser(awardId, awardTeamExamine.getUser_id(),awardTeamExamine.getContribution());
                }
                awardTeamExamineMapper.delAwardTeamExamineTeam(awardExamineId);
                rs = new Result("200", "审核结果已反馈", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查看个人所有获奖成果
     * @param award
     * @param award_time_start
     * @param award_time_end
     * @param pageSize
     * @param pageNum
     * @return
     * @throws ParseException
     */
    @Override
    public Result selectAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException {

        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
//            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();
            //查询条件封装到map
            map.put("achievement_name", award.getAchievement_name());
            map.put("award_name", award.getAward_name());
            map.put("issuing_authority", award.getIssuing_authority());
            map.put("approval_number", award.getApproval_number());
            map.put("aod_id", award.getAod_id());
            map.put("dr_id", award.getDr_id());
            map.put("ar_id", award.getAr_id());
            map.put("al_id", award.getAl_id());
            map.put("sc_id", award.getSc_id());
            map.put("subject_id", award.getSubject_id());
            map.put("sd_id", award.getSd_id());
            map.put("at_id", award.getAt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            //string转化为date，并且设置称timestamp格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!award_time_start.equals("")) {
                map.put("award_time_start", sdf.parse(award_time_start));
            }
            if (!award_time_end.equals("")) {
                map.put("award_time_end", sdf.parse(award_time_end));
            }
//            User user = userDao.findUserById(uid);
            List<UserAuth> userAuths = roleAuthMapper.findUserAuth(user.getRole_id());
            int cnt =0;
            //查看是否具有所有权限
            for (UserAuth userAuth : userAuths) {
                if (userAuth.getAuth_resource().equals("/awards/allAwards")){
                    cnt=1;
                }
            }
           if (cnt==1){
               List<Award> awards = awardMapper.selectAwardByCondition(map);
               Integer total = awardMapper.selectTotalAward(map);
               Map<String, Object> map1 = new HashMap<>();
               map1.put("total", total);
               list = new ArrayList<>();
               list.add(map1);
               List<Map> mapList = new ArrayList<Map>();
               for(int i = 0; i < awards.size(); i++) {
                   Map temp = JSON.parseObject(JSON.toJSONString(awards.get(i)), Map.class);
                   temp.put("authorName", userDao.findUserById(awards.get(i).getLeader_id()).getUser_name());
                   temp.put("award_time", sdf.format(awards.get(i).getAward_time()));
                   mapList.add(temp);
               }
               list.add(mapList);
           }else {
               map.put("leader_id", uid);
               List<Award> awards = awardMapper.selectAwardByCondition(map);
               Integer total = awardMapper.selectTotalAward(map);
               Map<String, Object> map1 = new HashMap<>();
               map1.put("total", total);
               list = new ArrayList<>();
               list.add(map1);
               List<Map> mapList = new ArrayList<Map>();
               for(int i = 0; i < awards.size(); i++) {
                   Map temp = JSON.parseObject(JSON.toJSONString(awards.get(i)), Map.class);
                   temp.put("authorName", userDao.findUserById(awards.get(i).getLeader_id()).getUser_name());
                   temp.put("award_time", sdf.format(awards.get(i).getAward_time()));
                   mapList.add(temp);
               }
               list.add(mapList);
           }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 更新获奖成果
     * @param award
     * @return
     */
    @Override
    public Result updateAward(Award award) {
        System.out.println();
        awardMapper.updateAward(award);
        rs = new Result("200", "更新成功", null);
        return rs;
    }

    /**
     * 根据id删除某个获奖成果
     * @param award_id
     * @return
     */
    @Override
    public Result delAwardById(Integer award_id) {
        try {
            awardMapper.delAwardById(award_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询所有获奖成果
     *
     * @param award
     * @param award_time_start
     * @param award_time_end
     * @param pageSize
     * @param pageNum
     * @return
     * @throws ParseException
     */
    @Override
    public Result selectAllAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();
            //查询条件封装到map
            map.put("achievement_name", award.getAchievement_name());
            map.put("award_name", award.getAward_name());
            map.put("issuing_authority", award.getIssuing_authority());
            map.put("approval_number", award.getApproval_number());
            map.put("aod_id", award.getAod_id());
            map.put("dr_id", award.getDr_id());
            map.put("ar_id", award.getAr_id());
            map.put("al_id", award.getAl_id());
            map.put("sc_id", award.getSc_id());
            map.put("subject_id", award.getSubject_id());
            map.put("sd_id", award.getSd_id());
            map.put("at_id", award.getAt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            //string转化为date，并且设置称timestamp格式

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!award_time_start.equals("")) {
                map.put("award_time_start", sdf.parse(award_time_start));
            }
            if (!award_time_end.equals("")) {
                map.put("award_time_end", sdf.parse(award_time_end));
            }
            System.out.println(map);
            //根据条件获取所有成果
            List<Award> awards = awardMapper.selectAwardByCondition(map);
            //根据条件获取所有成果数量
            Integer total = awardMapper.selectTotalAward(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            //查询好所有组员信息，并且设置好格式便于前端接收使用
            List<Map> mapList = new ArrayList<Map>();
            for(int i = 0; i < awards.size(); i++) {
                Map temp = JSON.parseObject(JSON.toJSONString(awards.get(i)), Map.class);
                temp.put("authorName", userDao.findUserById(awards.get(i).getLeader_id()).getUser_name());
                temp.put("award_time", sdf.format(awards.get(i).getAward_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 根据成果id获取某个获奖详细信息
     * @param award_id
     * @return
     */
    @Override
    public Result findAwardById(Integer award_id) {
        try {
            Award award = awardMapper.findAwardById(award_id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
           //获取当前登录用户信息
            User user = (User) session.getAttribute("user");
            Map map = JSON.parseObject(JSON.toJSONString(award), Map.class);
            map.put("user_name", user.getUser_name());
            map.put("award_time", sdf.format(award.getAward_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 获取个人某个项目所有信息
     * @param award_id
     * @return
     */
    public  Result findPersonalAwardMessage(Integer award_id){
        try {
            //获奖成果信息
            Award award = awardMapper.findAwardById(award_id);
            //组员信息
            List<AwardTeamMap> awardTeamMaps = awardTeamMapper.selectAwardTeam(award_id);
            Map<String,Object> map =new HashMap<>();
            map.put("award",award);
            map.put("awardTeamMaps",awardTeamMaps);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
