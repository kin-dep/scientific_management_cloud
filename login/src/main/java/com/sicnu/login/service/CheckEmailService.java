package com.sicnu.login.service;

import com.sicnu.login.util.Result;

import javax.mail.MessagingException;

public interface CheckEmailService {
    Result addCheckCode(String email) throws MessagingException;

    Result findByCode(Integer check_code);

}
