package com.sicnu.login.controller;

import com.sicnu.login.service.impl.CheckEmailServiceImpl;
import com.sicnu.login.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RestController
@CrossOrigin
public class CheckEmailController {
    @Resource
    CheckEmailServiceImpl check_emailService;

    @RequestMapping("/user/addCode")
    public Result addCheckCode(@RequestParam("user_email") String user_email) throws MessagingException {
        Result rs = null;
        try {
            rs = check_emailService.addCheckCode(user_email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @RequestMapping("/user/checkCode")
    public Result CheckCode(Integer check_code) {
        Result rs = null;
        try {
            rs = check_emailService.findByCode(check_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
