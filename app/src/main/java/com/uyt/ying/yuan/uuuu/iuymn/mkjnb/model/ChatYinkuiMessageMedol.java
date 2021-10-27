package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;

public class ChatYinkuiMessageMedol implements Serializable {
    private String nickname; //昵称
    private String pointGrade;//会员等级
    private String bettingPrice;//下注金额
    private String lotteryTotalPrice;//中奖金额
    private String profitAndLoss;//盈亏

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPointGrade() {
        return pointGrade;
    }

    public void setPointGrade(String pointGrade) {
        this.pointGrade = pointGrade;
    }


    public String getBettingPrice() {
        return bettingPrice;
    }

    public void setBettingPrice(String bettingPrice) {
        this.bettingPrice = bettingPrice;
    }

    public String getLotteryTotalPrice() {
        return lotteryTotalPrice;
    }

    public void setLotteryTotalPrice(String lotteryTotalPrice) {
        this.lotteryTotalPrice = lotteryTotalPrice;
    }

    public String getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(String profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    public ChatYinkuiMessageMedol(String nickname, String pointGrade, String bettingPrice, String lotteryTotalPrice, String profitAndLoss) {
        this.nickname = nickname;
        this.pointGrade = pointGrade;

        this.bettingPrice = bettingPrice;
        this.lotteryTotalPrice = lotteryTotalPrice;
        this.profitAndLoss = profitAndLoss;
    }
}
