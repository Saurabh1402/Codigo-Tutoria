package com.massacre.codigotutoria.dbhelper;

import com.massacre.codigotutoria.exception.MySQLErrorCodesTranslator;
import com.massacre.codigotutoria.models.LanguageHeader;
import com.massacre.codigotutoria.models.LanguageIndex;
import com.massacre.codigotutoria.models.ProgrammingLanguage;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.postgresql.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        return null;
    }

    @Override
    public ProgrammingLanguage findLanguageByLanguageId(long languageId) {
        String query="SELECT language_id, title, image_resource,color_primary, color_accent, color_primary_dark," +
                "language_header_id,header_title,header_count," +
                "language_index_id, index_title, index_count " +
                "FROM programminglanguage pl, languageheader lh, languageindex li" +
                "WHERE pl.language_id=lh.programming_language_id and " +
                "lh.language_header_id=li.language_header_id and language_id=:language_id";
        Map<String,Object> mapper=new HashMap<String,Object>();
        mapper.put("language_id",languageId);
        namedParameterJdbcTemplate.query(query,mapper,new ProgrammingLanguageExtractorById());
        return null;
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
    public class ProgrammingLanguageExtractorById implements ResultSetExtractor<ProgrammingLanguage>{

        @Override
        public ProgrammingLanguage extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            ProgrammingLanguage programmingLanguage=new ProgrammingLanguage();
            programmingLanguage.setLanguageId(resultSet.getLong("language_id"));
            programmingLanguage.setTitle(resultSet.getString("title"));
            programmingLanguage.setImageResource(resultSet.getString("image_resource"));
            programmingLanguage.setColorPrimary(resultSet.getString("color_primary"));
            programmingLanguage.setColorPrimaryDark(resultSet.getString("color_primary_dark"));
            programmingLanguage.setColorAccent(resultSet.getString("color_accent"));
            programmingLanguage.setHeaders(new ArrayList<LanguageHeader>());
            HashMap<Long,LanguageHeader> map=new HashMap<Long,LanguageHeader>();
            while(resultSet.next()){
                long sqlHeaderId=resultSet.getLong("language_header_id");
                LanguageHeader languageHeader=map.get(sqlHeaderId);
                if(languageHeader==null){
                    languageHeader=new LanguageHeader();
                    languageHeader.setHeaderTitle(resultSet.getString("header_title"));
                    languageHeader.setLanguageHeaderId(sqlHeaderId);
                    languageHeader.setHeaderCount(resultSet.getLong("header_count"));
                    languageHeader.setIndex(new ArrayList<LanguageIndex>());
                    programmingLanguage.getHeaders().add(languageHeader);
                }
                LanguageIndex languageIndex=new LanguageIndex();
                languageIndex.setIndexTitle(resultSet.getString("index_title"));
                languageIndex.setLanguageIndexId(resultSet.getLong("language_index_id"));
                languageIndex.setIndexCount(resultSet.getLong("index_count"));
            }
            return null;
        }
    }
    public class ProgrammingLanguageExtractor implements ResultSetExtractor<List<ProgrammingLanguage>>{

        @Override
        public List<ProgrammingLanguage> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            return null;
        }
    }

}
