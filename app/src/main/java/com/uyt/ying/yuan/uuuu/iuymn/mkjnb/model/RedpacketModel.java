

/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class RedpacketModel extends CommonModel {
    String touxiangUrl;
    String nickname;
    String time;
    String price;

    public RedpacketModel(String touxiangUrl, String nickname, String time, String price) {
        this.touxiangUrl = touxiangUrl;
        this.nickname = nickname;
        this.time = time;
        this.price = price;
    }

    public String getTouxiangUrl() {
        return touxiangUrl;
    }

    public void setTouxiangUrl(String touxiangUrl) {
        this.touxiangUrl = touxiangUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
