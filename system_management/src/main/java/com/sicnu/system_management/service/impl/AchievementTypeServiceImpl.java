package com.sicnu.system_management.service.impl;

import com.sicnu.system_management.component.RedisUtil;
import com.sicnu.system_management.mapper.AchievementTypeMapper;
import com.sicnu.system_management.pojo.AchievementType;
import com.sicnu.system_management.service.AchievementTypeService;
import com.sicnu.system_management.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *成果类型字典数据表
 */
@Service
@Slf4j
public class AchievementTypeServiceImpl implements AchievementTypeService {
    @Resource
    AchievementTypeMapper achievementTypeMapper;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    private Result rs;

    /**
     * 添加成果类型数据
     * @param at_name
     * @return
     */
    @Override
    public Result addAchievementType(String at_name) {
        try {
            AchievementType achievementType = achievementTypeMapper.selectAchievementTypeByName(at_name);
            if (achievementType==null) {
                achievementTypeMapper.addAchievementType(at_name);
                redisUtil.del("achievementTypes");
                rs = new Result("200", "插入成功", null);
            }else
            {
                rs = new Result("400", "字典数据已存在，切勿重复插入", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据类型id删除成果类型
     * @param at_id
     * @return
     */
    @Override
    public Result delAchievementType(Integer at_id) {

        try {
            achievementTypeMapper.delAchievementType(at_id);
            redisUtil.del("achievementTypes");
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * 获取所有成果类型
     * @return
     */
    @Override

    public Result findAllAchievementType() {

        try {
            List achievementTypes = new ArrayList<>();
            if (redisUtil.hasKey("achievementTypes")) {
                log.warn("从redis中获取数据.");
                achievementTypes = redisList.get("achievementTypes", 0, -1);
            } else {
                achievementTypes = achievementTypeMapper.findAllAchievementType();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("achievementTypes", achievementTypes);
                log.info("成功存入redis.");
            }
            rs = new Result("200", null, achievementTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * 更新成果类型数据
     * @param achievementType
     * @return
     */
    @Override
    public Result updateAchievementType(AchievementType achievementType) {
        try {
            achievementTypeMapper.updateAchievementType(achievementType);
            redisUtil.del("achievementTypes");
            rs =  new Result("200","更改字典数据成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
