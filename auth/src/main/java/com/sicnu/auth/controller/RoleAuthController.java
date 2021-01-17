package com.sicnu.auth.controller;


import com.sicnu.auth.service.impl.RoleAuthServiceImpl;
import com.sicnu.auth.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class RoleAuthController {
    @Resource
    RoleAuthServiceImpl roleAuthService;
    private Result rs;

    @GetMapping("/roleAuth/updateAuth")

    public Result updateAuth(@RequestParam(value = "role_id") Integer role_id, @RequestParam(value = "authList") Integer[] authList) {
        try {
            rs = roleAuthService.updateAuth(role_id, authList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
