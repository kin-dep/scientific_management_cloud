package com.sicnu.login.controller;


import com.sicnu.login.pojo.Anounce;
import com.sicnu.login.service.impl.AnounceServiceImpl;
import com.sicnu.login.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@Controller
@ResponseBody
public class AnounceController {
    @Resource
    AnounceServiceImpl anounceService;

    private Result rs;

    @PostMapping("/anounce/addAnounce")
    public Result addAnounce(Anounce anounce) {
        try {
            rs = anounceService.addAnounce(anounce);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/anounce/delAnounceById")
    public Result delAnounceById(Integer anounce_id) {
        try {
            rs = anounceService.delAnounceById(anounce_id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/anounce/updateAnounce")
    public Result updateAnounce(Anounce anounce) {
        try {
            rs = anounceService.updateAnounce(anounce);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/anounce/selectAllAnounce")
    public Result selectAllAnounce(@RequestBody Map<String, Object> condition) {
        try {
            rs = anounceService.selectAllAnounce(condition);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/anounce/selectAnounceById")
    public Result selectAnounceById(Integer anounce_id) {
        try {
            rs = anounceService.selectAnounceById(anounce_id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
