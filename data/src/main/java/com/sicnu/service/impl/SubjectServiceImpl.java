package com.sicnu.service.impl;

import com.sicnu.mapper.SubjectMapper;
import com.sicnu.pojo.Subject;
import com.sicnu.service.SubjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 一级学科
 */
@Service
@RestController
@RequestMapping("/subject")
public class SubjectServiceImpl implements SubjectService {
    private Result rs;
    @Resource
    SubjectMapper subjectMapper;

    @Override
    @RequestMapping("/addSubject")

    public Result addSubject(String subject_id, String subject_name, String sc_id) {
        try {
            Subject subject = subjectMapper.selectSubjectByName(subject_name);
            if (subject == null) {
                subjectMapper.addSubject(subject_id, subject_name, sc_id);
                rs = new Result("200", "添加一级学科成功", null);

            } else {
                rs = new Result("400", "C", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @RequestMapping("/delSubject")
    public Result delSubject(String subject_id) {
        try {
            subjectMapper.delSubject(subject_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    @RequestMapping("/findAllSubject")

    public Result findAllSubject() {
        try {
            List<Subject> subjects = subjectMapper.findAllSubject();
            rs = new Result("200", null, subjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @RequestMapping("/updateSubject")

    public Result updateSubject(Subject subject) {
        try {
            subjectMapper.updateSubject(subject);
            rs = new Result("200", "更改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }


}
