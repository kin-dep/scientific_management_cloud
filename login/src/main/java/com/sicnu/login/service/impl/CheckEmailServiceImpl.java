package com.sicnu.login.service.impl;


import com.sicnu.login.mapper.CheckEmailMapper;
import com.sicnu.login.mapper.UserMapper;
import com.sicnu.login.pojo.CheckEmail;
import com.sicnu.login.service.CheckEmailService;
import com.sicnu.login.util.Code;
import com.sicnu.login.util.Result;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 邮箱验证码模拟短信验证
 */
@Service
public class CheckEmailServiceImpl implements CheckEmailService {
    @Resource
    JavaMailSenderImpl mailSender;
    @Resource
    CheckEmailMapper check_emailDao;
    /**
     * 产生验证码
     * @param email
     * @return
     * @throws MessagingException
     */
    @Override
    public Result addCheckCode(String email) throws MessagingException {
        Integer check_code;
        Date overtime;
        Result rs = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            Date overTime = new Date(date.getTime() + 300000);
            check_code = new Code().smsCode();
            List<CheckEmail> check_emails = check_emailDao.findAllCode();
            for (int i = 0; i < check_emails.size(); i++) {
                if (check_emails.get(i).getCheck_code().equals(check_code) ) {
                    check_code = new Code().smsCode();
                }
            }

            check_emailDao.addCheckCode(email, check_code, overTime);
            rs = new Result("200", "验证码发送成功", null);


            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
            helper.setSubject("高校科研管理系统注册验证码");
            helper.setText("<p>您的注册码为：<span style='color:blue;text-decoration:underline'>" + check_code + "</span>,该注册码将会在5分钟(" + overTime + ")后失效，请您尽快进注册验证！</p>", true);
            helper.setTo(email);
            helper.setFrom("1776557392@qq.com");
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MailException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 验证码验证
     * @param check_code
     * @return
     */
    @Override
    public Result findByCode(Integer check_code) {
        Result rs = null;
        try {
            CheckEmail check_email = check_emailDao.findByCode(check_code);
            if (check_email == null){
                rs = new Result("401", "该验证码无效", null);
                return rs;
            }
            Date endTime = check_email.getOvertime();
            Date date = new Date();
            Date startTime = new Date(date.getTime());
            /**
             * y验证过程
             */
            if (startTime.after(endTime)) {
                rs = new Result("400", "验证码已经失效,请重新获取验证码", null);
                check_emailDao.delCode(check_code);
            } else {
                rs = new Result("200", "验证成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


}
