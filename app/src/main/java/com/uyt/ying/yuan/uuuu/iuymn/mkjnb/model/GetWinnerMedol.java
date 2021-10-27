package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class GetWinnerMedol implements Serializable {
    String imageURl;
    String nickName;
    String lotteryName;
    BigDecimal price;
    String lotteryId;

    String sex;
    String title;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ArrayList<String> getStringList() {
        return stringList;
    }

    public void setStringList(ArrayList<String> stringList) {
        this.stringList = stringList;
    }

    String grade;
    ArrayList<String> stringList;

    public GetWinnerMedol(String imageURl, String nickName, String lotteryName, BigDecimal price, String lotteryId, String sex, String title, String grade, ArrayList<String> stringList) {
        this.imageURl = imageURl;
        this.nickName = nickName;
        this.lotteryName = lotteryName;
        this.price = price;
        this.lotteryId = lotteryId;
        this.sex = sex;
        this.title = title;
        this.grade = grade;
        this.stringList = stringList;
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }
}
