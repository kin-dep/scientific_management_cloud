package com.sicnu.service;

import com.sicnu.pojo.SubjectCategory;
import com.sicnu.util.Result;

public interface SubjectCategoryService {
    Result addSubjectCategory(String sc_id, String sc_name);

    Result delSubjectCategory(String sc_id);

    Result findAllSubjectCategory();

    Result selectSubjectCategory(String sc_id);
    Result updateSubjectCategory(SubjectCategory subjectCategory);
}
