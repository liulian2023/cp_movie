package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class RakeBackModel implements Serializable {
    String remake;
    String createDate;
    String price;

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public RakeBackModel(String remake, String createDate, String price) {

        this.remake = remake;
        this.createDate = createDate;
        this.price = price;
    }
}
