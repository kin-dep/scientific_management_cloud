package com.sicnu.service.impl;

import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.Role;
import com.sicnu.service.RoleService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色
 */
@Service
@RestController
@RequestMapping("/role")

public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;
    private Result rs = null;

    @Override
    @RequestMapping("/addRole")

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
    @RequestMapping("/delRole")

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
    @RequestMapping("/findAllRole")

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
    @RequestMapping("/getRole")

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
    @RequestMapping("/selectRoleName")

    public Result selectRoleName(Integer role_id) {
        String role_name = roleMapper.selectRoleName(role_id);
        rs = new Result("200", null, role_name);
        return rs;
    }


}
