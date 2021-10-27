package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class HelpGuideMedol implements Serializable {
    String theme;
    String id;
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HelpGuideMedol(String theme, String id,String content) {

        this.theme = theme;
        this.id = id;
        this.content = content;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {

        return theme;
    }


}
