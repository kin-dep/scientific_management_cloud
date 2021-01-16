package com.sicnu.login.controller;


import com.sicnu.login.pojo.Permission;
import com.sicnu.login.pojo.PermissionTreeNode;
import com.sicnu.login.pojo.User;
import com.sicnu.login.service.UserService;
import com.sicnu.login.util.PermissionTreeBuilder;
import com.sicnu.login.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单资源控制器
 */
@Controller
@CrossOrigin
public class MenuController {

    @Resource
    private UserService userService;

    @Resource
    private HttpSession session;

    /**
     * 查询用户权限，返回权限树
     *
     * @return
     */
    @PostMapping("/user/getUserMenu")
    @ResponseBody
    public Result getUserMenu() {
        List<PermissionTreeNode> permissionTree = null;
        try {
            User user = (User) session.getAttribute("user");
            if(user == null) return new Result("404", "登录过期", null);
            Set<Permission> userPermissions = userService.queryPermissionByUserId(user.getUser_id());
//        System.out.println("permissionNodes:"+userPermissions);

            Set<Permission> userPermissionSet = new HashSet<>(userPermissions);

            System.out.println("userPermissionSet:" + userPermissionSet);

            List<PermissionTreeNode> permissionNodes = new ArrayList<>();

            for (Permission permission : userPermissionSet) {
                permissionNodes.add(new PermissionTreeNode(permission, new ArrayList<>()));
            }
            System.out.println("permissionNodes" + permissionNodes);
            permissionTree = PermissionTreeBuilder.build(permissionNodes, Permission.ROOTID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result("200", "成功查询到权限数据", permissionTree);
    }

    @PostMapping("/user/queryPermissionByRoleId")
    @ResponseBody
    public Result queryPermissionByRoleId(Integer role_id) {
        List<PermissionTreeNode> permissionTree = null;
        try {
            Set<Permission> userPermissions = userService.queryPermissionByRoleId(role_id);
            System.out.println("permissionNodes:" + userPermissions);

            Set<Permission> userPermissionSet = new HashSet<>(userPermissions);

            System.out.println("userPermissionSet:" + userPermissionSet);

            List<PermissionTreeNode> permissionNodes = new ArrayList<>();

            for (Permission permission : userPermissionSet) {
                permissionNodes.add(new PermissionTreeNode(permission, new ArrayList<>()));
            }
            System.out.println("permissionNodes" + permissionNodes);
            permissionTree = PermissionTreeBuilder.build(permissionNodes, Permission.ROOTID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result("200", "成功查询到权限数据", permissionTree);
    }

    @PostMapping("/user/queryAllPermission")
    @ResponseBody
    public Result queryAllPermission() {
        List<PermissionTreeNode> permissionTree = null;
        try {
            Set<Permission> userPermissions = userService.queryAllPermission();
            System.out.println("permissionNodes:" + userPermissions);

            Set<Permission> userPermissionSet = new HashSet<>(userPermissions);

            System.out.println("userPermissionSet:" + userPermissionSet);

            List<PermissionTreeNode> permissionNodes = new ArrayList<>();

            for (Permission permission : userPermissionSet) {
                permissionNodes.add(new PermissionTreeNode(permission, new ArrayList<>()));
            }
            System.out.println("permissionNodes" + permissionNodes);
            permissionTree = PermissionTreeBuilder.build(permissionNodes, Permission.ROOTID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result("200", "成功查询到权限数据", permissionTree);
    }

}
