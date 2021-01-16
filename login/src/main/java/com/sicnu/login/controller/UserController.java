package com.sicnu.login.controller;


import com.sicnu.login.pojo.User;
import com.sicnu.login.service.impl.UserServiceImpl;
import com.sicnu.login.util.IpUtil;
import com.sicnu.login.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户信息操作 连接
 */
@RestController
@CrossOrigin
@Slf4j
public class UserController {
    @Resource
    UserServiceImpl userService;

    private Result rs = null;

    /**
     * 用户登录
     *
     * @param user_act
     * @param user_pwd
     * @param session
     * @return
     */
    @RequestMapping("/user/login")
    public Result login(@RequestParam("user_act") String user_act, @RequestParam("user_pwd") String user_pwd, HttpSession session, HttpServletRequest request) {
        try {
            String user_ip = IpUtil.getIpAddr(request);
            ThreadContext.put("ol_ip", user_ip);
            ThreadContext.put("ol_type", "登录");
            ThreadContext.put("ol_uid", "-1");
            ThreadContext.put("ol_uname", "none");
            rs = userService.findByUserAct(user_act, user_pwd, session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 用户注册
     *
     * @param user_act
     * @param user_pwd
     * @param user_name
     * @param user_email
     * @param user_number
     * @param user_id_number
     * @param user_state
     * @param department_id
     * @param role_id
     * @return
     * @throws MessagingException
     */
    @RequestMapping("/user/register")
    public Result addUser(String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id) throws MessagingException {
        try {

            rs = userService.addUser(user_act, user_pwd, user_name, user_email, user_number, user_id_number, user_state, department_id, role_id);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 用户修改密码
     *
     * @param user_act
     * @param user_pwd
     * @param user_email
     * @param user_number
     * @param user_id_number
     * @return
     */
    @RequestMapping("/user/updatePwd")
    public Result updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number) {
        try {
            rs = userService.updatePwd(user_act, user_pwd, user_email, user_number, user_id_number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 用户账号状态更改
     *
     * @param user_id
     * @param user_state
     * @return
     */
    @RequestMapping("/user/changeStatus")
    public Result changeStatus(Integer user_id, Integer user_state) {
        try {
            rs = userService.changeStatus(user_id, user_state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 用户退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/user/loginOut")
    public Result loginOut(HttpSession session) {
        try {
            rs = userService.loginOut(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 用户没有登录
     *
     * @return
     */
    @RequestMapping("/user/un_auth")
    public Result unAuth() {
        try {
            rs = new Result("404", "用户未登录！", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @RequestMapping("/user/findAllUser")
    public Result findAllUser() {
        try {
            rs = userService.findAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @RequestMapping("/user/updateUserRole")
    public Result updateUserRole(Integer role_id, Integer user_id) {
        try {
            rs = userService.updateUserRole(role_id, user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @RequestMapping("/user/findUserById")
    public Result findUserById(Integer user_id) {
        try {
            rs = userService.findUserById(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @RequestMapping("/user/findNameId")
    public Result findNameId(String user_name) {
        try {
            rs  = userService.findNameId(user_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/user/selectUserByCondition")
    public Result selectUserByCondition(@RequestBody Map<String, Object> map){
        try {
            System.out.println("map=" + map);
            rs = userService.selectUserByCondition(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/user/updateUserMessage")
    public Result updateUserMessage(User user) {
        try {
            rs = userService.updateUserMessage(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}