package com.massacre.codigotutoria.models;

/**
 * Created by saurabh on 16/7/17.
 */
public class LanguageIndex {

    private long languageIndexId;
    private String indexTitle;
    private long indexCount;

    public long getLanguageIndexId() {
        return languageIndexId;
    }

    public void setLanguageIndexId(long languageIndexId) {
        this.languageIndexId = languageIndexId;
    }

    public String getIndexTitle() {
        return indexTitle;
    }

    public void setIndexTitle(String indexTitle) {
        this.indexTitle = indexTitle;
    }

    public long getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(long indexCount) {
        this.indexCount = indexCount;
    }

}
