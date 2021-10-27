package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

/*
首页中奖信息recycleView数据适配
 */
public class Reward implements Serializable {
    private String name;
    private String time;
    private String money;
    private String imgIdTop;
    private String imageIdBottom;
    private  String lotteryName;

    public String getImgIdTop() {
        return imgIdTop;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public Reward(String name, String time, String money, String imgIdTop, String imageIdBottom, String lotteryName) {
        this.name = name;
        this.time = time;
        this.money = money;
        this.imgIdTop = imgIdTop;
        this.imageIdBottom = imageIdBottom;
        this.lotteryName = lotteryName;
    }

    public void setImgIdTop(String imgIdTop) {
        this.imgIdTop = imgIdTop;
    }

    public String getImageIdBottom() {
        return imageIdBottom;
    }

    public void setImageIdBottom(String imageIdBottom) {
        this.imageIdBottom = imageIdBottom;
    }

    public Reward(String name, String time, String money, String imgIdTop, String imageIdBottom) {

        this.name = name;
        this.time = time;
        this.money = money;
        this.imgIdTop = imgIdTop;
        this.imageIdBottom = imageIdBottom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


}
