package com.sicnu.service;

import com.sicnu.pojo.Language;
import com.sicnu.util.Result;

public interface LanguageService {
    Result addLanguage(String language_name);
    Result delLanguage(Integer language_id);
    Result findAllLanguage();
    Result updateLanguage(Language language);
}
