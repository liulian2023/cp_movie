package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class NoticeModel implements Serializable {
    String title;
    String content;
    String time;
    Long id;
    String wenContent;

    public String getWenContent() {
        return wenContent;
    }

    public void setWenContent(String wenContent) {
        this.wenContent = wenContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NoticeModel(String title, String content, String time, Long id,String wenContent) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.id = id;
        this.wenContent = wenContent;

    }
}
