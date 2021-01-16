package com.sicnu.data.service;

import com.sicnu.data.Language;
import com.sicnu.data.Result;

public interface LanguageService {
    Result addLanguage(String language_name);
    Result delLanguage(Integer language_id);
    Result findAllLanguage();
    Result updateLanguage(Language language);
}
