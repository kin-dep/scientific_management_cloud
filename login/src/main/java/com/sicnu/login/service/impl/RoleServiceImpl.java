package com.sicnu.login.service.impl;


import com.sicnu.login.mapper.RoleMapper;
import com.sicnu.login.pojo.Role;
import com.sicnu.login.service.RoleService;
import com.sicnu.login.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;
    private Result rs = null;

    @Override
    public Result addRole(String role_name) {
        try {
            Role role = roleMapper.selectRoleByName(role_name);
            if (role == null) {
                roleMapper.addRole(role_name);
                rs = new Result("200", "添加成功", null);
            } else {
                rs = new Result("400", "该角色已存在", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delRole(Integer role_id) {

        try {
            roleMapper.delRole(role_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllRole() {
        try {
            List<Role> roles = roleMapper.findAllRole();
            rs = new Result("200", null, roles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public List<Role> getRole(Integer role_id) {
        List<Role> roles = null;
        try {
            roles = roleMapper.getRole(role_id);
            rs = new Result("200", null, roles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Result selectRoleName(Integer role_id) {
        String role_name = roleMapper.selectRoleName(role_id);
        rs = new Result("200", null, role_name);
        return rs;
    }


}
