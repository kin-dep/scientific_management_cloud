package com.sicnu.login.service;


import com.sicnu.login.util.Result;

import java.util.List;

public interface AuthService {
    Result addAuth(String auth_name);

    Result delAuth(Integer auth_id);

    Result findAllAuth();

    List<Object> getAuth(Integer role_id);

    Result findAuthByPid(Integer user_id, Integer auth_pid);
}
