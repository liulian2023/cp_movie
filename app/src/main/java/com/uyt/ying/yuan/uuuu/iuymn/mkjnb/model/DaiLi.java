package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class DaiLi implements Serializable {
    String text;
    int imgId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public DaiLi(String text, int imgId) {
        this.text = text;
        this.imgId = imgId;
    }

    public DaiLi(String text) {
        this.text = text;
    }
}
