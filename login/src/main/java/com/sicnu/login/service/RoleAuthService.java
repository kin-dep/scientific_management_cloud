package com.sicnu.login.service;


import com.sicnu.login.util.Result;

public interface RoleAuthService {
    Result updateAuth(Integer role_id, Integer[] authList);
}
