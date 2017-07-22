package com.massacre.codigotutoria.dbhelper.extractor;

import com.massacre.codigotutoria.models.LanguageHeader;
import com.massacre.codigotutoria.models.LanguageIndex;
import com.massacre.codigotutoria.models.ProgrammingLanguage;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by saurabh on 23/7/17.
 */
public class ProgrammingLanguageExtractorFindAll implements ResultSetExtractor<List<ProgrammingLanguage>> {

    @Override
    public List<ProgrammingLanguage> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, LanguageHeader> mapHeader = new HashMap<Long, LanguageHeader>();
        Map<Long, ProgrammingLanguage> mapLanguage = new HashMap<Long, ProgrammingLanguage>();
        ProgrammingLanguage programmingLanguage=null;
        LanguageHeader languageHeader=null;
        while(resultSet.next()){
            programmingLanguage=null;
            languageHeader=null;
            long sqlLanguageId=resultSet.getLong("language_id");
            programmingLanguage=mapLanguage.get(sqlLanguageId);
            if(programmingLanguage==null){
                programmingLanguage=new ProgrammingLanguage();
                programmingLanguage.setLanguageId(resultSet.getLong("language_id"));
                programmingLanguage.setTitle(resultSet.getString("title"));
                programmingLanguage.setImageResource(resultSet.getString("image_resource"));
                programmingLanguage.setColorPrimary(resultSet.getString("color_primary"));
                programmingLanguage.setColorPrimaryDark(resultSet.getString("color_primary_dark"));
                programmingLanguage.setColorAccent(resultSet.getString("color_accent"));
                programmingLanguage.setHeaders(new ArrayList<LanguageHeader>());
                mapLanguage.put(sqlLanguageId,programmingLanguage);
            }
            if(programmingLanguage==null){System.out.println(sqlLanguageId);}
            long sqlHeaderId=resultSet.getLong("language_header_id");
            languageHeader=mapHeader.get(sqlHeaderId);
            if(languageHeader==null){
                languageHeader=new LanguageHeader();
                languageHeader.setLanguageHeaderId(sqlHeaderId);
                languageHeader.setHeaderTitle(resultSet.getString("header_title"));
                languageHeader.setHeaderCount(resultSet.getLong("header_count"));
                languageHeader.setIndex(new ArrayList<LanguageIndex>());
                programmingLanguage.getHeaders().add(languageHeader);
                mapHeader.put(languageHeader.getLanguageHeaderId(),languageHeader);
            }

            LanguageIndex languageIndex=new LanguageIndex();
            languageIndex.setIndexTitle(resultSet.getString("index_title"));
            languageIndex.setLanguageIndexId(resultSet.getLong("language_index_id"));
            languageIndex.setIndexCount(resultSet.getLong("index_count"));
            languageHeader.getIndex().add(languageIndex);
        }

        return new ArrayList<ProgrammingLanguage>(mapLanguage.values());

    }
}
