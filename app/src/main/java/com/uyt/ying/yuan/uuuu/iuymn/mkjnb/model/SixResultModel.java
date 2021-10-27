/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

public class SixResultModel extends CommonModel  {
    String qishu;
    String time;
    String lotteryNo;
    String animalAll;
    String color;
   ;

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(String lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public String getAnimalAll() {
        return animalAll;
    }

    public void setAnimalAll(String animalAll) {
        this.animalAll = animalAll;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public SixResultModel(String qishu, String time, String lotteryNo, String animalAll, String color) {

        this.qishu = qishu;
        this.time = time;
        this.lotteryNo = lotteryNo;
        this.animalAll = animalAll;
        this.color = color;
    }
}
