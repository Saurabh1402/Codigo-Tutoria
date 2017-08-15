package com.massacre.codigotutoria.dbhelper;


/**
 * Created by saurabh on 23/7/17.
 */

public class ProgrammingContract {
    private ProgrammingContract(){}
    public static class ProgrammingLanguageDefinition{
        public static String TABLE_NAME = "programminglanguage";
        public static String COLUMN_LANGUAGE_ID="language_id";
        public static String COLUMN_TITLE="title";
        public static String COLUMN_IMAGE_RESOURCE="image_resource";
        public static String COLUMN_COLOR_PRIMARY="color_primary";
        public static String COLUMN_COLOR_PRIMARY_DARK="color_primary_dark";
        public static String COLUMN_COLOR_ACCENT="color_accent";
        public static String COLUMN_LAST_MODIFIED ="last_modified";

    }
    public static class LanguageHeaderDefinition{
        public static String TABLE_NAME = "languageheader";
        public static String COLUMN_LANGUAGE_HEADER_ID="language_header_id";
        public static String COLUMN_HEADER_TITLE="header_title";
        public static String COLUMN_HEADER_COUNT="header_count";
        public static String COLUMN_PROGRAMMING_LANGUAGE_ID="programming_language_id";
        public static String COLUMN_LAST_MODIFIED ="last_modified";

    }
    public static class LanguageIndexDefinition{
        public static String TABLE_NAME = "programminglanguage";
        public static String COLUMN_LANGUAGE_INDEX_ID="language_index_id";
        public static String COLUMN_INDEX_TITLE="index_title";
        public static String COLUMN_INDEX_COUNT="index_count";
        public static String COLUMN_PROGRAMMING_HEADER_ID="programming_header_id";
        public static String COLUMN_LAST_MODIFIED ="last_modified";
    }
}
