package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class GouCaiDaTin implements Serializable {
    String  imgUrl;
    String content;

    public GouCaiDaTin(String imgUrl, String content) {

        this.imgUrl = imgUrl;
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
