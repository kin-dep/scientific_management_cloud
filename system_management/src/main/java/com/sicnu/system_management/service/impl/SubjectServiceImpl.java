package com.sicnu.system_management.service.impl;

import com.sicnu.system_management.mapper.SubjectMapper;
import com.sicnu.system_management.pojo.Subject;
import com.sicnu.system_management.service.SubjectService;
import com.sicnu.system_management.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 一级学科
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    private Result rs;
    @Resource
    SubjectMapper subjectMapper;

    @Override
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
