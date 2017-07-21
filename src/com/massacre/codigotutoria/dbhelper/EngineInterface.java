package com.massacre.codigotutoria.dbhelper;

import com.massacre.codigotutoria.models.ProgrammingLanguage;

import java.util.List;

/**
 * Created by saurabh on 18/7/17.
 */
public interface EngineInterface {
    List<ProgrammingLanguage> findAllLanguage();
    ProgrammingLanguage findLanguageByLanguageId(long languageId);
    ProgrammingLanguage findLanguageByHeaderId(long headerId);
    ProgrammingLanguage findLanguageByIndexId(long indexId);
    void insertLanguage(ProgrammingLanguage language);
    void updateLanguage(ProgrammingLanguage language);
    void deleteLanguage(ProgrammingLanguage language);
    String checkConnectivity();
}
