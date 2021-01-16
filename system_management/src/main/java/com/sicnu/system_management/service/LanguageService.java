package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.Language;
import com.sicnu.system_management.util.Result;

public interface LanguageService {
    Result addLanguage(String language_name);
    Result delLanguage(Integer language_id);
    Result findAllLanguage();
    Result updateLanguage(Language language);
}
