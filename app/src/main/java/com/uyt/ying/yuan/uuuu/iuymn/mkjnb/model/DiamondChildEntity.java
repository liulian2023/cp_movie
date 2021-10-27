package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class DiamondChildEntity {
    String name;
    String url;
    String countContent;

    boolean isGet;

    public boolean isGet() {
        return isGet;
    }

    public void setGet(boolean get) {
        isGet = get;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountContent() {
        return countContent;
    }

    public void setCountContent(String countContent) {
        this.countContent = countContent;
    }

    public DiamondChildEntity(String name, String url, String countContent, boolean isGet) {
        this.name = name;
        this.url = url;
        this.countContent = countContent;
        this.isGet = isGet;
    }
}
