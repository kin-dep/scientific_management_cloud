package com.sicnu.achievement.service.impl;


import com.alibaba.fastjson.JSON;
import com.sicnu.achievement.mapper.*;
import com.sicnu.achievement.pojo.User;
import com.sicnu.achievement.pojo.UserAuth;
import com.sicnu.achievement.pojo.Book;
import com.sicnu.achievement.pojo.BookTeamExamine;
import com.sicnu.achievement.pojo.BookTeamMap;
import com.sicnu.achievement.service.BookService;
import com.sicnu.achievement.util.Result;
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

@Service
public class BookServiceImpl implements BookService {


    @Resource
    BookMapper bookMapper;

    @Resource
    UserMapper userDao;

    @Resource
    BookExamineMapper bookExamineMapper;

    @Resource
    BookTeamMapper bookTeamMapper;
    @Resource
    BookTeamExamineMapper bookTeamExamineMapper;
    @Resource
    JavaMailSenderImpl mailSender;
    private Result rs;
    @Resource
    RoleAuthMapper roleAuthMapper;

    @Resource
    private HttpSession session;

    /**
     * 审核著作
     * @param book
     * @param checkMessage
     * @param message
     * @return
     */
    @Override
    public Result addBook(Book book, String checkMessage, String message) {
        try {
            Integer bookExamineId = bookExamineMapper.selectBookExamineId(book.getBook_name(),book.getLeader_id());
            List<BookTeamExamine> bookTeamExamines = bookTeamExamineMapper.selectBookTeamExamineById(bookExamineId);
            //获取项目负责人信息
            User user = userDao.findUserById(book.getLeader_id());
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
                bookTeamExamineMapper.delBookTeamExamineTeam(bookExamineId);
                bookExamineMapper.delBookExamine(book.getLeader_id(),book.getBook_name());
                rs = new Result("200", "审核结果已反馈", null);
            } else {
                bookMapper.addBook(book);
                //获取项目id 返给用户
                Integer bookId = bookMapper.selectBookId(book.getLeader_id(), book.getBook_name());
                helper.setSubject("高校科研管理系统：申请已通过");
                helper.setText("<p>您的项目申报审核成功，项目编号为：<span style='color:blue;text-decoration:underline'>" + bookId + "</span>,请勿遗忘。</p>", true);
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                for (BookTeamExamine bookTeamExamine : bookTeamExamines) {
                    bookTeamMapper.addBookTeamUser(bookId,bookTeamExamine.getUser_id(),bookTeamExamine.getContribution());
                }
                //从待审核删除
                bookTeamExamineMapper.delBookTeamExamineTeam(bookExamineId);
                bookExamineMapper.delBookExamine(book.getLeader_id(),book.getBook_name());
                rs = new Result("200", "审核结果已反馈", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询
     * @param book
     * @param publish_time_start
     * @param publish_time_end
     * @param pageSize
     * @param pageNum
     * @return
     * @throws ParseException
     */

    @Override
    public Result selectBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;

        try {
            User user = (User) session.getAttribute("user");

            //获取登录用户的id
            Integer uid = user.getUser_id();

        //查询条件封装
            Map<String, Object> map = new HashMap<>();
            map.put("book_name", book.getBook_name());
            map.put("press", book.getPress());
            map.put("pl_id", book.getPl_id());
            map.put("bt_id", book.getBt_id());
            map.put("pp_id", book.getPp_id());
            map.put("isbn", book.getIsbn());
            map.put("word_number", book.getWord_number());
            map.put("trans", book.getTrans());
            map.put("language_id", book.getLanguage_id());
            map.put("sc_id", book.getSc_id());
            map.put("subject_id", book.getSubject_id());
            map.put("aod_id", book.getAod_id());
            map.put("sd_id", book.getSd_id());
            map.put("rt_id", book.getRt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            System.err.println(map);
            //设置格式，把string转化为特定date格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
//            User user = userDao.findUserById(uid);
            List<UserAuth> userAuths = roleAuthMapper.findUserAuth(user.getRole_id());
            int cnt =0;
            for (UserAuth userAuth : userAuths) {
                if (userAuth.getAuth_resource().equals("/books/allBooks")){
                    cnt=1;
                }
            }
            //查询结果封装
            if (cnt==1){
                List<Book> books = bookMapper.selectBookByCondition(map);
                Integer total = bookMapper.selectTotalBook(map);
                Map<String, Object> map1 = new HashMap<>();
                map1.put("total", total);
                list = new ArrayList<>();
                list.add(map1);
                List<Map> mapList = new ArrayList<Map>();
                for(int i = 0; i < books.size(); i++) {
                    Map temp = JSON.parseObject(JSON.toJSONString(books.get(i)), Map.class);
                    temp.put("authorName", userDao.findUserById(books.get(i).getLeader_id()).getUser_name());
                    temp.put("publish_time", sdf.format(books.get(i).getPublish_time()));
                    mapList.add(temp);
                }
                list.add(mapList);
            }else{
                map.put("leader_id", uid);
                List<Book> books = bookMapper.selectBookByCondition(map);
                Integer total = bookMapper.selectTotalBook(map);
                Map<String, Object> map1 = new HashMap<>();
                map1.put("total", total);
                list = new ArrayList<>();
                list.add(map1);
                List<Map> mapList = new ArrayList<Map>();
                for(int i = 0; i < books.size(); i++) {
                    Map temp = JSON.parseObject(JSON.toJSONString(books.get(i)), Map.class);
                    temp.put("authorName", userDao.findUserById(books.get(i).getLeader_id()).getUser_name());
                    temp.put("publish_time", sdf.format(books.get(i).getPublish_time()));
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
     * 根据id删除某一个著作
     * @param book_id
     * @return
     */
    @Override
    public Result delBookById(Integer book_id) {
        try {
//            Book book = bookMapper.selectBookByCondition();
            bookMapper.delBookById(book_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 更改某一个著作的信息
     * @param book
     * @return
     */
    @Override
    public Result updateBook(Book book) {
        try {
            bookMapper.updateBook(book);
            rs = new Result("200","更新成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查看符合条件的所有著作
     * @param book
     * @param publish_time_start
     * @param publish_time_end
     * @param pageSize
     * @param pageNum
     * @return
     * @throws ParseException
     */
    @Override
    public Result selectAllBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;
        try {
            //封装查询条件
            Map<String, Object> map = new HashMap<>();
            map.put("book_name", book.getBook_name());
            map.put("press", book.getPress());
            map.put("pl_id", book.getPl_id());
            map.put("bt_id", book.getBt_id());
            map.put("pp_id", book.getPp_id());
            map.put("isbn", book.getIsbn());
            map.put("word_number", book.getWord_number());
            map.put("trans", book.getTrans());
            map.put("language_id", book.getLanguage_id());
            map.put("sc_id", book.getSc_id());
            map.put("subject_id", book.getSubject_id());
            map.put("aod_id", book.getAod_id());
            map.put("sd_id", book.getSd_id());
            map.put("rt_id", book.getRt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            //设置转化时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            //封装结果集
            List<Book> books = bookMapper.selectBookByCondition(map);
            Integer total = bookMapper.selectTotalBook(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            List<Map> mapList = new ArrayList<Map>();
            for(int i = 0; i < books.size(); i++) {
                Map temp = JSON.parseObject(JSON.toJSONString(books.get(i)), Map.class);
                temp.put("authorName", userDao.findUserById(books.get(i).getLeader_id()).getUser_name());
                temp.put("publish_time", sdf.format(books.get(i).getPublish_time()));
                mapList.add(temp);
            }
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 根据id查看某一个著作信息
     * @param book_id
     * @return
     */
    @Override
    public Result findBookById(Integer book_id) {
        try {
            Book book = bookMapper.findBookById(book_id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            User user = (User) session.getAttribute("user");
            Map map = JSON.parseObject(JSON.toJSONString(book), Map.class);
            map.put("user_name", user.getUser_name());
            map.put("publish_time", sdf.format(book.getPublish_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 查看某个著作所有信息
     * @param book_id
     * @return
     */
    public  Result findPersonalBookMessage(Integer book_id){
        try {
            Book book = bookMapper.findBookById(book_id);
            List<BookTeamMap> bookTeamMaps = bookTeamMapper.selectBookTeam(book_id);
            Map<String,Object> map =new HashMap<>();
            map.put("book",book);
            map.put("bookTeamMaps",bookTeamMaps);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
