package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class MessageModel implements Serializable {
    String title;
    String content;
    String time;
    Long id;
    Long isRead;
    String webContent;

    public String getWebContent() {
        return webContent;
    }

    public void setWebContent(String webContent) {
        this.webContent = webContent;
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

    public Long getIsRead() {
        return isRead;
    }

    public void setIsRead(Long isRead) {
        this.isRead = isRead;
    }

    public MessageModel(String title, String content,String time, Long id, Long isRead,String webContent) {

        this.title = title;
        this.content = content;
        this.time = time;
        this.id = id;
        this.isRead = isRead;
        this.webContent = webContent;
    }
}
