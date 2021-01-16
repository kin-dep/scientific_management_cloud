package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.Subject;
import com.sicnu.system_management.util.Result;

public interface SubjectService {
    Result addSubject(String subject_id, String subject_name, String sc_id);

    Result delSubject(String subject_id);

    Result findAllSubject();
    Result updateSubject(Subject subject);

}
