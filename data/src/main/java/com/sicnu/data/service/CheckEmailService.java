package com.sicnu.data.service;

import com.sicnu.data.Result;

import javax.mail.MessagingException;

public interface CheckEmailService {
    Result addCheckCode(String email) throws MessagingException;

    Result findByCode(Integer check_code);

}
