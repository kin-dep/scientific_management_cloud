package com.sicnu.login.service;



import com.sicnu.login.pojo.Role;
import com.sicnu.login.util.Result;

import java.util.List;


public interface RoleService {
    Result addRole(String role_name);

    Result delRole(Integer role_id);

    Result findAllRole();

    List<Role> getRole(Integer role_id);
    Result selectRoleName(Integer role_id);
}
