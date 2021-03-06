package com.sicnu.system_management.controller;

import com.sicnu.system_management.pojo.BookType;
import com.sicnu.system_management.service.impl.BookTypeServiceImpl;
import com.sicnu.system_management.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
/**
 * 著作类型
 */
public class BookTypeController {

    @Resource
    BookTypeServiceImpl bookTypeService;
    private Result rs = null;
    @PostMapping("/bookType/addBookType")
    @RequiresPermissions("/data")
    public Result addBookType(String bt_name) {

        try {
            rs =bookTypeService.addBookType(bt_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }
    @PostMapping("/bookType/delBookType")
    @RequiresPermissions("/data")
    public Result delBookType(Integer bt_id) {

        try {
            rs = bookTypeService.delBookType(bt_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/bookType/findAllBookType")
    public Result findAllBookType() {
        try {
            rs = bookTypeService.findAllBookType();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/bookType/updateBookType")
    @RequiresPermissions("/data")
    public Result updateBookType(BookType bookType) {
        try {
           rs= bookTypeService.updateBookType(bookType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
