package com.massacre.codigotutoria.dbhelper;

import com.massacre.codigotutoria.extractor.ProgrammingLanguageExtractorById;
import com.massacre.codigotutoria.extractor.ProgrammingLanguageExtractorFindAll;
import com.massacre.codigotutoria.exception.MySQLErrorCodesTranslator;
import com.massacre.codigotutoria.models.ProgrammingContainer;
import com.massacre.codigotutoria.models.ProgrammingLanguage;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.*;

/**
 * Created by saurabh on 18/7/17.
 */
public class DatabaseEngine implements EngineInterface{

    private Logger logger;
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    private Connection getConnection() throws SQLException{
//
//    }
    public DatabaseEngine(){}
    @Override
    public String checkConnectivity() {
        String query="select title from programminglanguage where language_id=3";
       return namedParameterJdbcTemplate.queryForObject(query,new HashMap<>(),String.class);

    }

    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
        NamedParameterJdbcTemplate jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        MySQLErrorCodesTranslator errorCodesTranslator=new MySQLErrorCodesTranslator();
        errorCodesTranslator.setDataSource(dataSource);
        //jdbcTemplate.setExceptionTranslator(errorCodesTranslator);
        this.namedParameterJdbcTemplate=jdbcTemplate;
    }

    
    @Override
    public List<ProgrammingLanguage> findAllLanguage() {
        String query="SELECT pl.language_id, pl.title, pl.image_resource,pl.color_primary, pl.color_accent, pl.color_primary_dark, pl.last_modified, " +
                " lh.language_header_id,lh.header_title,lh.header_count, lh.last_modified, " +
                "li.language_index_id, li.index_title, li.index_count,li.last_modified " +
                "FROM programminglanguage pl, languageheader lh, languageindex li " +
                "WHERE pl.language_id=lh.programming_language_id and " +
                "lh.language_header_id=li.language_header_id";
        return namedParameterJdbcTemplate.query(query,new ProgrammingLanguageExtractorFindAll());
    }

    @Override
    public ProgrammingLanguage findLanguageByLanguageId(long languageId) {
        String query="SELECT pl.language_id, pl.title, pl.image_resource,pl.color_primary, pl.color_accent, pl.color_primary_dark, pl.last_modified, " +
                " lh.language_header_id,lh.header_title,lh.header_count, lh.last_modified, " +
                "li.language_index_id, li.index_title, li.index_count li.last_modified " +
                "FROM programminglanguage pl, languageheader lh, languageindex li " +
                "WHERE pl.language_id=lh.programming_language_id and " +
                "lh.language_header_id=li.language_header_id and pl.language_id=:languageId";
        Map<String,Object> mapper=new HashMap<String,Object>();
        mapper.put("languageId",languageId);
        return namedParameterJdbcTemplate.query(query,mapper,new ProgrammingLanguageExtractorById());

    }

    public List<ProgrammingLanguage> findExcludedLanguage(List<ProgrammingContainer> excludeLanguage){
        System.out.println("Inside findExcludedLanguage of Database");
            String  query="SELECT pl.language_id, pl.title, pl.image_resource,pl.color_primary, pl.color_accent, pl.color_primary_dark, pl.last_modified, " +
                            " lh.language_header_id,lh.header_title,lh.header_count, lh.last_modified, " +
                            "li.language_index_id, li.index_title, li.index_count, li.last_modified " +
                            "FROM programminglanguage pl, languageheader lh, languageindex li " +
                            "WHERE pl.language_id=lh.programming_language_id and " +
                            "lh.language_header_id=li.language_header_id" +
                            " and pl.language_id NOT IN (:languageList )";
        MapSqlParameterSource mapper = new MapSqlParameterSource();
            Set<Long> languageIdsExcluded = new HashSet<Long>();
            for (ProgrammingContainer programmingContainer : excludeLanguage) {
                languageIdsExcluded.add(programmingContainer.getLanguageId());
            }
            mapper.addValue("languageList", languageIdsExcluded);
            System.out.println("LanguageList length before calling query: " + languageIdsExcluded.size());
            List<ProgrammingLanguage> languageList = namedParameterJdbcTemplate.query(query, mapper, new ProgrammingLanguageExtractorFindAll());
            System.out.println("languageList after calling query" + languageList.size());
            return languageList;

    }

    @Override
    public ProgrammingLanguage findLanguageByHeaderId(long headerId) {
        return null;
    }

    @Override
    public ProgrammingLanguage findLanguageByIndexId(long indexId) {
        return null;
    }

    @Override
    public void insertLanguage(ProgrammingLanguage language) {

    }

    @Override
    public void updateLanguage(ProgrammingLanguage language) {

    }

    @Override
    public void deleteLanguage(ProgrammingLanguage language) {

    }


}
