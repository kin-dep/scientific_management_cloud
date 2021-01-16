package com.sicnu.data.service;

import com.sicnu.data.Subject;
import com.sicnu.data.Result;

public interface SubjectService {
    Result addSubject(String subject_id, String subject_name, String sc_id);

    Result delSubject(String subject_id);

    Result findAllSubject();
    Result updateSubject(Subject subject);

}
