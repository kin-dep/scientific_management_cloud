package com.sicnu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.data.BookExamineMapper;
import com.sicnu.data.BookTeamExamineMapper;
import com.sicnu.data.CacheUserMapper;
import com.sicnu.data.UserMapper;
import com.sicnu.data.*;
import com.sicnu.data.BookTeamMap;
import com.sicnu.data.BookExamineService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookExamineServiceImpl implements BookExamineService {

    @Resource
    BookExamineMapper bookExamineMapper;

    private Result rs;

    @Resource
    BookTeamExamineMapper bookTeamExamineMapper;
    @Resource
    CacheUserMapper cacheUserMapper;
    @Resource
    private HttpSession session;
    @Resource
    UserMapper userMapper;

    /**
     * 提交审核著作
     * @param bookExamine
     * @param user_id
     * @param contribution
     * @return
     */
    @Override
    public Result addBookExamine(BookExamine bookExamine,Integer[] user_id,Integer[] contribution) {
        try {
            //通过部分信息获取id
            Integer be_id1 = bookExamineMapper.selectBookExamineId(bookExamine.getBook_name(),bookExamine.getLeader_id());
            //判断是否已经存在
            if(be_id1 !=null){
                rs = new Result("401","切勿重复提交审核",null);
                return rs;
            }
            bookExamine.setExamine_status("未审核");
            bookExamine.setApply_time(new Date());
            bookExamine.setReviewer_id(1);
            //添加审核信息
            bookExamineMapper.addBookExamine(bookExamine);
            Integer be_id = bookExamineMapper.selectBookExamineId(bookExamine.getBook_name(),bookExamine.getLeader_id());
            for (int i = 0; i < user_id.length; i++) {
                bookTeamExamineMapper.addBookTeamExamineUser(be_id,user_id[i],contribution[i]);
            }
            rs = new Result("200","您的著作成果已经上传审核，请您耐心等待审核结果",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询待审核著作
     * @param bookExamine
     * @param publish_time_start
     * @param publish_time_end
     * @param pageSize
     * @param pageNum
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    @Override
    public Result selectBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;

        try {

            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
//            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();
            //把查询条件封装到map里面
            Map<String, Object> map = new HashMap<>();
            map.put("book_name", bookExamine.getBook_name());
            map.put("leader_id", uid);
            map.put("press", bookExamine.getPress());
            map.put("pl_id", bookExamine.getPl_id());
            map.put("bt_id", bookExamine.getBt_id());
            map.put("pp_id", bookExamine.getPp_id());
            map.put("isbn", bookExamine.getIsbn());
            map.put("word_number", bookExamine.getWord_number());
            map.put("trans", bookExamine.getTrans());
            map.put("language_id", bookExamine.getLanguage_id());
            map.put("sc_id", bookExamine.getSc_id());
            map.put("subject_id", bookExamine.getSubject_id());
            map.put("aod_id", bookExamine.getAod_id());
            map.put("sd_id", bookExamine.getSd_id());
            map.put("rt_id", bookExamine.getRt_id());
            map.put("examine_status",bookExamine.getExamine_status());
            map.put("reviewer_id",bookExamine.getReviewer_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            //string转化为date，并且设置格式
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
            //根据条件查询待审核著作
            List<BookExamine> bookExamines = bookExamineMapper.selectBookExamineByCondition(map);
            Integer total = bookExamineMapper.selectTotalBookExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            //组员信息设置
            for(BookExamine be : bookExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(be), Map.class);
                temp.put("authorName", userMapper.findUserById(be.getLeader_id()).getUser_name());
                temp.put("publish_time", sdf.format(be.getPublish_time()));
                temp.put("apply_time", sdf.format(be.getApply_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 根据ae_id删除待审核信息
     * @param be_id
     * @return
     */
    @Override
    public Result delBookExamineById(Integer be_id) {
        try {

//            BookExamine bookExamine = bookExamineMapper.selectBookExamineByCondition();
            bookExamineMapper.delBookExamineById(be_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据id查看待审核项目详细信息
     * @param be_id
     * @return
     */
    @Override
    public Result findBookExamineById(Integer be_id) {
        try {

            BookExamine bookExamine =bookExamineMapper.findBookExamineById(be_id);
            rs = new Result("200",null,bookExamine);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            User user = (User) session.getAttribute("user");
            Map map = JSON.parseObject(JSON.toJSONString(bookExamine), Map.class);
            map.put("user_name", user.getUser_name());
            map.put("apply_time", sdf.format(bookExamine.getApply_time()));
            map.put("publish_time", sdf.format(bookExamine.getPublish_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询所有待审核著作
     * @param bookExamine
     * @param publish_time_start
     * @param publish_time_end
     * @param pageSize
     * @param pageNum
     * @param apply_time_start
     * @param apply_time_end
     * @return
     */
    public Result selectAllBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;

        try {
            //封装查询条件到map
            Map<String, Object> map = new HashMap<>();
            map.put("book_name", bookExamine.getBook_name());
            map.put("press", bookExamine.getPress());
            map.put("pl_id", bookExamine.getPl_id());
            map.put("bt_id", bookExamine.getBt_id());
            map.put("pp_id", bookExamine.getPp_id());
            map.put("isbn", bookExamine.getIsbn());
            map.put("word_number", bookExamine.getWord_number());
            map.put("translate", bookExamine.getTrans());
            map.put("language_id", bookExamine.getLanguage_id());
            map.put("sc_id", bookExamine.getSc_id());
            map.put("subject_id", bookExamine.getSubject_id());
            map.put("aod_id", bookExamine.getAod_id());
            map.put("sd_id", bookExamine.getSd_id());
            map.put("rt_id", bookExamine.getRt_id());
            map.put("examine_status",bookExamine.getExamine_status());
            map.put("reviewer_id",bookExamine.getReviewer_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            //设置时间格式，把string转化为时间
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
            //获取信息
            List<BookExamine> bookExamines = bookExamineMapper.selectBookExamineByCondition(map);
            Integer total = bookExamineMapper.selectTotalBookExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            //结果封装
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<>();
            for(BookExamine be : bookExamines) {
                Map temp = JSON.parseObject(JSON.toJSONString(be), Map.class);
                temp.put("authorName", userMapper.findUserById(be.getLeader_id()).getUser_name());
                temp.put("publish_time", sdf.format(be.getPublish_time()));
                temp.put("apply_time", sdf.format(be.getApply_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 根据id获取某个待审核项目所有信息
     * @param be_id
     * @return
     */
    public Result findPersonalBookExamineMessage(Integer be_id){
        try {
            List<BookTeamMap>bookTeamMaps = bookTeamExamineMapper.selectBookTeamExamineUser(be_id);
            BookExamine bookExamine = bookExamineMapper.findBookExamineById(be_id);
            Map<String,Object> map = new HashMap<>();
            map.put("bookExamine",bookExamine);
            map.put("bookTeamMaps",bookTeamMaps);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
