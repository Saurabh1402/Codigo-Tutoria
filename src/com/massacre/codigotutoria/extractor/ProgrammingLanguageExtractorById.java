package com.massacre.codigotutoria.extractor;

import com.massacre.codigotutoria.models.LanguageHeader;
import com.massacre.codigotutoria.models.LanguageIndex;
import com.massacre.codigotutoria.models.ProgrammingLanguage;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saurabh on 23/7/17.
 */
public class ProgrammingLanguageExtractorById implements ResultSetExtractor<ProgrammingLanguage> {

    @Override
    public ProgrammingLanguage extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        HashMap<Long, LanguageHeader> map = new HashMap<Long, LanguageHeader>();
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        if(resultSet.next()) {
            programmingLanguage.setLanguageId(resultSet.getLong("language_id"));
            programmingLanguage.setTitle(resultSet.getString("title"));
            programmingLanguage.setImageResource(resultSet.getString("image_resource"));
            programmingLanguage.setColorPrimary(resultSet.getString("color_primary"));
            programmingLanguage.setColorPrimaryDark(resultSet.getString("color_primary_dark"));
            programmingLanguage.setColorAccent(resultSet.getString("color_accent"));
            programmingLanguage.setHeaders(new ArrayList<LanguageHeader>());
            long sqlHeaderId = resultSet.getLong("language_header_id");
            LanguageHeader languageHeader = new LanguageHeader();
            languageHeader.setHeaderTitle(resultSet.getString("header_title"));
            languageHeader.setLanguageHeaderId(sqlHeaderId);
            languageHeader.setHeaderCount(resultSet.getLong("header_count"));
            languageHeader.setIndex(new ArrayList<LanguageIndex>());
            programmingLanguage.getHeaders().add(languageHeader);
            map.put(languageHeader.getLanguageHeaderId(),languageHeader);
            LanguageIndex languageIndex = new LanguageIndex();
            languageIndex.setIndexTitle(resultSet.getString("index_title"));
            languageIndex.setLanguageIndexId(resultSet.getLong("language_index_id"));
            languageIndex.setIndexCount(resultSet.getLong("index_count"));
            languageHeader.getIndex().add(languageIndex);
        }else {
            return null;
        }


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
                map.put(languageHeader.getLanguageHeaderId(),languageHeader);
            }

            LanguageIndex languageIndex=new LanguageIndex();
            languageIndex.setIndexTitle(resultSet.getString("index_title"));
            languageIndex.setLanguageIndexId(resultSet.getLong("language_index_id"));
            languageIndex.setIndexCount(resultSet.getLong("index_count"));
            languageHeader.getIndex().add(languageIndex);
        }
        System.out.println(" My Message: "+programmingLanguage.getHeaders().size());
        return programmingLanguage;
    }
}
