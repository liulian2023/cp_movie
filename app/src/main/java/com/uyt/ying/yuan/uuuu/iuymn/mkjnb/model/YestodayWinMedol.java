package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class YestodayWinMedol implements Serializable {
    String imageURl;
    String nickName;
    BigDecimal price;
    String lotteryId;

    String sex;
    String title;
    String grade;
    ArrayList<String> stringList;


    public YestodayWinMedol(String imageURl, String nickName, BigDecimal price, String lotteryId, String title, String sex, String grade, ArrayList<String> stringList) {
        this.imageURl = imageURl;
        this.nickName = nickName;
        this.price = price;
        this.lotteryId = lotteryId;
        this.title = title;
        this.sex = sex;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
}
