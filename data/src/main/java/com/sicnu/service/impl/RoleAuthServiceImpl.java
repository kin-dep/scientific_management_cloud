package com.sicnu.service.impl;

import com.sicnu.mapper.RoleAuthMapper;
import com.sicnu.service.RoleAuthService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Service
@RestController
@RequestMapping("/roleAuth")

public class RoleAuthServiceImpl implements RoleAuthService {
    @Resource
    RoleAuthMapper roleAuthMapper;

    private Result rs = null;

    @Override
    @RequestMapping("/updateAuth")

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
