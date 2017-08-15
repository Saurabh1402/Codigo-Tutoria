package com.massacre.codigotutoria.models;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by saurabh on 28/7/17.
 */

public class ProgrammingContainer {
    private long languageId;
    private Date lastModified;

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this,ProgrammingContainer.class);

    }

    public static ProgrammingContainer getFromJson(String json){
        return new Gson().fromJson(json,ProgrammingContainer.class);
    }

    public static List<ProgrammingContainer> getListFromJson(String json) throws JSONException{
        JSONArray jsonArray=new JSONArray(json);
        List<ProgrammingContainer> languageList=new ArrayList<ProgrammingContainer>();
        for (int i = 0; i < jsonArray.length(); i++) {
            languageList.add(ProgrammingContainer.getFromJson(languageList.get(i).toString()));
        }
        return languageList;
    }
}
