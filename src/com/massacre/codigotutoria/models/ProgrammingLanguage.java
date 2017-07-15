package com.massacre.codigotutoria.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by saurabh on 13/7/17.
 */
@XmlRootElement(name = "ProgrammingLanguage")
public class ProgrammingLanguage {
    private String title;
    private String imageResource;
    private String colorPrimary;
    private String colorPrimaryDark;
    private String colorAccent;
    private LanguageHeader headers[];

    public LanguageHeader[] getHeaders() {
        return headers;
    }

    public void setHeaders(LanguageHeader[] headers) {
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


}
