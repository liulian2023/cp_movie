package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class UserImage  implements Serializable {
    String url;

    public UserImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
//    int id;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public UserImage(int id) {
//
//        this.id = id;
//    }
}
