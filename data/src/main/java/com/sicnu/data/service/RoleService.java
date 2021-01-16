package com.sicnu.data.service;

import com.sicnu.data.Role;
import com.sicnu.data.Result;

import java.util.List;


public interface RoleService {
    Result addRole(String role_name);

    Result delRole(Integer role_id);

    Result findAllRole();

    List<Role> getRole(Integer role_id);
    Result selectRoleName(Integer role_id);
}
