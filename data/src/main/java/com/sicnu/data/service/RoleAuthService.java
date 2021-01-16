package com.sicnu.data.service;

import com.sicnu.data.Result;

public interface RoleAuthService {
    Result updateAuth(Integer role_id, Integer[] authList);
}
