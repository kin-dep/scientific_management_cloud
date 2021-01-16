package com.sicnu.data.service;

import com.sicnu.data.SubjectCategory;
import com.sicnu.data.Result;

public interface SubjectCategoryService {
    Result addSubjectCategory(String sc_id, String sc_name);

    Result delSubjectCategory(String sc_id);

    Result findAllSubjectCategory();

    Result selectSubjectCategory(String sc_id);
    Result updateSubjectCategory(SubjectCategory subjectCategory);
}
