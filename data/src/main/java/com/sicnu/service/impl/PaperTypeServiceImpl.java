package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.PaperTypeMapper;
import com.sicnu.pojo.PaperType;
import com.sicnu.service.PaperTypeService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 论文类型字典操作
 */
@Service
@Slf4j
@RestController
@RequestMapping("/paperType")
public class PaperTypeServiceImpl implements PaperTypeService {
    Result rs = null;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    @Resource
    PaperTypeMapper paperTypeMapper;
    @RequestMapping("/addPaperType")
    @Override
    public Result addPaperType(String pt_name) {
        try {
            PaperType paperType = paperTypeMapper.selectPaperTypeByName(pt_name);
            if (paperType != null) {
                rs = new Result("401", "该字典已经存在", null);
            } else {
                paperTypeMapper.addPaperType(pt_name);
                redisUtil.del("paperTypes");
                rs = new Result("200", "字典插入成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @RequestMapping("/delPaperType")
    @Override
    public Result delPaperType(Integer pt_id) {
        try {
            paperTypeMapper.delPaperType(pt_id);
            redisUtil.del("paperTypes");

            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @RequestMapping("/findAllPaperType")
    @Override
    public Result findAllPaperType() {
        try {
            List paperTypes = new ArrayList();

            if (redisUtil.hasKey("paperTypes")) {
                log.warn("从redis中获取数据.");
                paperTypes = redisList.get("paperTypes", 0, -1);
            } else {
                paperTypes = paperTypeMapper.findAllPaperType();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("paperTypes", paperTypes);
                log.info("成功存入redis.");
            }
            rs = new Result("200", null, paperTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
    @RequestMapping("/updatePaperType")
    @Override
    public Result updatePaperType(PaperType paperType) {
        try {
            if (paperType!=null){
                paperTypeMapper.updatePaperType(paperType);
                redisUtil.del("paperTypes");
                rs = new Result("200", "更改成功", null);
            }else{
                rs = new Result("400","更改数据不能为空",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

}
