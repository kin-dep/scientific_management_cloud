package com.sicnu.auth.service.impl;


import com.sicnu.auth.mapper.RoleAuthMapper;
import com.sicnu.auth.service.RoleAuthService;
import com.sicnu.auth.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleAuthServiceImpl implements RoleAuthService {
    @Resource
    RoleAuthMapper roleAuthMapper;

    private Result rs = null;

    @Override
    public Result updateAuth(Integer role_id, Integer[] authList) {

        try {
            roleAuthMapper.delAuth(role_id);
            for (Integer authid : authList) {
                roleAuthMapper.addRole(role_id, authid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs = new Result("200", "更改成功", null);
    }
}
