package com.sicnu.examine.controller;


import com.sicnu.examine.pojo.BookExamine;
import com.sicnu.examine.service.impl.BookExamineServiceImpl;
import com.sicnu.examine.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class BookExamineController {

    @Resource
    BookExamineServiceImpl bookExamineService;
    private Result rs;


    @GetMapping("/bookExamine/addBookExamine")
   public Result addBookExamine(BookExamine bookExamine, Integer[] user_id, Integer[] contribution) {
       try {
           rs=bookExamineService.addBookExamine(bookExamine, user_id, contribution);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return rs;
    }
    @PostMapping("/bookExamine/selectBookExamineByCondition")
    public Result selectBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        try {
           rs = bookExamineService.selectBookExamineByCondition(bookExamine, publish_time_start, publish_time_end, pageSize, pageNum, apply_time_start, apply_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/bookExamine/delBookExamineById")
    public Result delBookExamineById(Integer be_id) {
        try {
            rs= bookExamineService.delBookExamineById(be_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/bookExamine/selectAllBookExamineByCondition")
    public Result selectAllBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
          rs = bookExamineService.selectAllBookExamineByCondition(bookExamine, publish_time_start, publish_time_end, pageSize, pageNum, apply_time_start, apply_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/bookExamine/findBookExamineById")
    public Result findBookExamineById(Integer be_id) {
        try {
            rs =bookExamineService.findBookExamineById(be_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/bookExamine/findPersonalAwardExamineMessage")
    public Result findPersonalAwardExamineMessage(Integer be_id){
        try {
            rs = bookExamineService.findPersonalBookExamineMessage(be_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
