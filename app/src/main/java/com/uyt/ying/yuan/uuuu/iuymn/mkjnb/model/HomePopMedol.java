package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class HomePopMedol implements Serializable {
    String title;
    String titleSmall;
    String content;
    String imgUrl;
    int status=0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleSmall() {
        return titleSmall;
    }

    public void setTitleSmall(String titleSmall) {
        this.titleSmall = titleSmall;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public HomePopMedol(String title, String titleSmall, String content, String imgUrl) {
        this.title = title;
        this.titleSmall = titleSmall;
        this.content = content;
        this.imgUrl = imgUrl;
    }
}
