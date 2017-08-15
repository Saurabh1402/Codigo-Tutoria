package com.massacre.codigotutoria.extractor;

import com.massacre.codigotutoria.models.LanguageHeader;
import com.massacre.codigotutoria.models.LanguageIndex;
import com.massacre.codigotutoria.models.ProgrammingLanguage;
import com.massacre.codigotutoria.utils.CodigoTutoriaDateFormatter;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by saurabh on 23/7/17.
 */
public class ProgrammingLanguageExtractorFindAll implements ResultSetExtractor<List<ProgrammingLanguage>> {

    @Override
    public List<ProgrammingLanguage> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        System.out.println("Inside extractData of Extractor");
        Map<Long, LanguageHeader> mapHeader = new HashMap<Long, LanguageHeader>();
        Map<Long, ProgrammingLanguage> mapLanguage = new HashMap<Long, ProgrammingLanguage>();
        ProgrammingLanguage programmingLanguage;
        LanguageHeader languageHeader;
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
                try {
                    programmingLanguage.setLastModified(new CodigoTutoriaDateFormatter().parse(resultSet.getString("last_modified")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                try {
                    languageHeader.setLastModified(new CodigoTutoriaDateFormatter().parse(resultSet.getString("last_modified")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }programmingLanguage.getHeaders().add(languageHeader);
                mapHeader.put(languageHeader.getLanguageHeaderId(),languageHeader);
            }

            LanguageIndex languageIndex=new LanguageIndex();
            languageIndex.setIndexTitle(resultSet.getString("index_title"));
            languageIndex.setLanguageIndexId(resultSet.getLong("language_index_id"));
            languageIndex.setIndexCount(resultSet.getLong("index_count"));
            try {
                languageIndex.setLastModified(new CodigoTutoriaDateFormatter().parse(resultSet.getString("last_modified")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            languageHeader.getIndex().add(languageIndex);
        }

        return new ArrayList<ProgrammingLanguage>(mapLanguage.values());

    }
}
