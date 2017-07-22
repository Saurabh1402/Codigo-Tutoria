package com.massacre.codigotutoria.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by saurabh on 13/7/17.
 */
@XmlRootElement(name = "ProgrammingLanguage")
public class ProgrammingLanguage {
    private long languageId;
    private String title;
    private String imageResource;
    private String colorPrimary;
    private String colorPrimaryDark;
    private String colorAccent;
    private List<LanguageHeader> headers;

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    public List<LanguageHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<LanguageHeader> headers) {
        this.headers = headers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(String colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public String getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public void setColorPrimaryDark(String colorPrimaryDark) {
        this.colorPrimaryDark = colorPrimaryDark;
    }

    public String getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(String colorAccent) {
        this.colorAccent = colorAccent;
    }

//    public String toString(){
//        return new Gson().toJson(this);
//    }
}
