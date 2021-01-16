package com.sicnu.auth.service;


import com.sicnu.auth.util.Result;

public interface RoleAuthService {
    Result updateAuth(Integer role_id, Integer[] authList);
}
