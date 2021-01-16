package com.sicnu.data.service.impl;

import com.sicnu.data.*;
import com.sicnu.data.CountService;
import com.sicnu.data.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountServiceImpl implements CountService {
    @Resource
    ProjectMapper projectMapper;
    @Resource
    PaperMapper paperMapper;
    @Resource
    BookMapper bookMapper;
    @Resource
    AwardMapper awardMapper;
    @Resource
    PatentMapper patentMapper;
    @Resource
    ProjectExamineMapper projectExamineMapper;
    @Resource
    PaperExamineMapper paperExamineMapper;
    @Resource
    BookExamineMapper bookExamineMapper;
    @Resource
    AwardExamineMapper awardExamineMapper;
    @Resource
    PatentExamineMapper patentExamineMapper;

    private Result rs;


    @Override
    public Result countAll() {
        try {
            List<Integer> countList = new ArrayList<>();
            countList.add(projectMapper.countProject());
            countList.add(paperMapper.countPaper());
            countList.add(bookMapper.countBook());
            countList.add(awardMapper.countAward());
            countList.add(patentMapper.countPatent());
            rs = new Result("200", "成功获取科研统计数据", countList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result countCheck() {
        try {
            List<Integer> countList = new ArrayList<>();
            countList.add(projectExamineMapper.countProjectExamine());
            countList.add(paperExamineMapper.countPaperExamine());
            countList.add(bookExamineMapper.countBookExamine());
            countList.add(awardExamineMapper.countAwardExamine());
            countList.add(patentExamineMapper.countPatentExamine());
            rs = new Result("200", "成功获取待审核数量列表", countList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
