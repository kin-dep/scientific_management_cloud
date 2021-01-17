package com.sicnu.service;

import com.sicnu.util.Result;

public interface RoleAuthService {
    Result updateAuth(Integer role_id, Integer[] authList);
}
